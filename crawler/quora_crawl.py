import jaydebeapi
from bs4 import BeautifulSoup
import time
from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.action_chains import ActionChains
import undetected_chromedriver as uc
from webdriver_manager.chrome import ChromeDriverManager
import requests
import googletrans
from datetime import datetime

### translator setting ###
translator = googletrans.Translator()

### h2 database connection ###
conn = jaydebeapi.connect("org.h2.Driver",
                          "jdbc:h2:tcp://localhost/~/issueabroad",
                          ["sa", ""],
                          "h2-1.4.200.jar")


### crawling start ###
curs = conn.cursor()
now = datetime.now().date()

# 꺼짐 방지
chrome_options = Options()
chrome_options.add_experimental_option("detach", True)

# 불필요한 에러메세지 없애기 
chrome_options.add_experimental_option("excludeSwitches", ["enable-logging"])

# 크롬드라이버 매너저를 통해 드라이버를 설치, 서비스를 만들어낸다
service = Service(executable_path=ChromeDriverManager().install())
driver = uc.Chrome()

dtype = "'미국'"
comment_cnt = "0"
# create_date = "20221130"
create_date = str(now)
create_date = create_date.replace("-", "")
view_cnt = "0"
web_site = "'Quora'"


# 무한스크롤이라서 좀 내려가야 게시글 더 보임 이거 나중에 처리
base_url = "https://www.quora.com/"

driver.implicitly_wait(10)
driver.maximize_window()
driver.get(base_url)

time.sleep(2)

driver.find_element(By.CSS_SELECTOR, "div.puppeteer_test_login_button_google").click()

time.sleep(30) # 30초 안에 구글 로그인 해야됨

# 무한스크롤
idx = 0
before_h = driver.execute_script("return window.scrollY")
while True:      
    if(idx == 20): #  적당히 조절 조절
        break
    idx = idx + 1  
    driver.find_element(By.CSS_SELECTOR, 'body').send_keys(Keys.END)
    time.sleep(4) # 시간은 적절히 조절하기

    after_h = driver.execute_script("return window.scrollY")

    if(after_h == before_h):
        break
    before_h = after_h

print("스크롤이 끝났습니다.")

articles = driver.find_elements(By.CSS_SELECTOR, "div.q-box.qu-hover--bg--darken>div.q-box>div.q-box>div.q-box>div>div.q-box.qu-mb--tiny>div>span>span>a>div>div>div>div>span")
# articles2 = soup.select("div.q-box.qu-hover--bg--darken>div.q-box>div.q-box>div.q-box>div>div.q-box.qu-mb--tiny>div>span>span>a")

number_of_article = 1

for article in articles:    

    time.sleep(3)
    children = article.find_elements(By.TAG_NAME, "span")
    if(len(children) >= 2):
        print("원하는 글이 아닙니다. 다른 글을 가져옵니다.")
        continue
        
    print(str(number_of_article) + "번째 글을 읽어오는 중입니다...")
    print()
    number_of_article = number_of_article + 1

    title = ""
    title += article.text
    title += ""

    print(title)
    print()

    target = children[0]    
    actions = ActionChains(driver)
    actions.move_to_element(target).perform()
    time.sleep(3)
    # target.send_keys(Keys.ENTER)
    # driver.execute_script('arguments[0].click()', article)            
    target.click()    
    time.sleep(3)
    driver.switch_to.window(driver.window_handles[-1])   
    time.sleep(3) 

    url = "'"
    url += driver.current_url
    url += "'"    

    print(url)
    print()

    cmp_url = url[9:22]        

    if(cmp_url != 'www.quora.com'):
        driver.close()
        time.sleep(4)
        driver.switch_to.window(driver.window_handles[0])
        print("원하는 글이 아닙니다. 다른 글을 가져옵니다.")
        continue
    
    time.sleep(3)

    mores = driver.find_elements(By.CSS_SELECTOR, "span.q-text.qu-cursor--pointer")
    
    idx2 = 0    

    for more in mores:
        if(idx2 == 2):
            break
        idx2 = idx2 + 1

        time.sleep(3)
        driver.execute_script('arguments[0].click()', more)        
        time.sleep(3)
    
    contents = driver.find_elements(By.CSS_SELECTOR, "span.CssComponent__CssInlineComponent-sc-1oskqb9-1>span.q-box")
        
    content = ""
    
    for k in contents:
        article_content = k.text        
        content += article_content        
        content += "----------------------------------------------------------------------------------------------"    

    print(content)
    print()

    translator = googletrans.Translator()
    
    try:
        origin_title = ""    
        origin_title_add = translator.translate(title, dest='ko', src='en').text        
        origin_title += origin_title_add
        origin_title += ""

        origin_content = ""        
        origin_content_add = translator.translate(content, dest='ko', src='en').text
        origin_content += origin_content_add
        origin_content += ""
    except:
        print("번역 과정에 오류가 발생해 해당 글은 저장하지 않습니다.")
        driver.close()
        driver.switch_to.window(driver.window_handles[0])
        continue
    

    print(origin_title)
    print()

    print(origin_content)
    print()    

    content = content.replace("'", "`")
    content = "'" + content + "'"

    origin_content = origin_content.replace("'", "`")
    origin_content = "'" + origin_content + "'"

    title = title.replace("'", "`")
    title = "'" + title + "'"

    origin_title = origin_title.replace("'", "`")
    origin_title = "'" + origin_title + "'"
    
    

    curs.execute(f"insert into crawl (dtype, type, comment_count, content, create_date, title, view_count, origin_content, origin_title, url, web_site) values ({dtype}, {dtype}, {comment_cnt}, {origin_content}, {create_date}, {origin_title}, {view_cnt}, {content}, {title}, {url}, {web_site})")
    print("글이 저장되었습니다.")

    driver.close()
    time.sleep(3)
    driver.switch_to.window(driver.window_handles[0])
            

print("모든 글을 가져왔습니다.")

### h2 database close ###
curs.close()
conn.close()
