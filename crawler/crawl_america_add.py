import jaydebeapi
from datetime import datetime
import time

### h2 database connection ###
conn = jaydebeapi.connect("org.h2.Driver",
                          "jdbc:h2:tcp://localhost/~/issueabroad",
                          ["sa", ""],
                          "h2-1.4.200.jar")


### data add start ###
curs = conn.cursor()
now = datetime.now().date()

sql = "insert into article (dtype, comment_count, content, create_date, title, view_count, origin_content, origin_title, url, web_site) values (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s)"
val = ("suggest", "0", "content_text", now, "title_text", "0", "origin_content_text", "origin_title_text", "url", "web_site")

# curs.execute(sql, val)

dtype = "'미국'"
comment_cnt = "0"
content = "'크롤링한미국게시판내용1123123412313512'"
# create_date = "20221130"
create_date = str(now)
create_date = create_date.replace("-", "")
title = "'크롤링한미국게시판제목"
view_cnt = "0"
origin_content = "'none'"
origin_title = "'none'"
url = "'none'"
web_site = "'none'"

for i in range(1, 200):
    title = "'크롤링한미국게시판제목"
    title = title + str(i) + "'"    
    curs.execute(f"insert into crawl (dtype, type, comment_count, content, create_date, title, view_count, origin_content, origin_title, url, web_site) values ({dtype}, {dtype}, {comment_cnt}, {content}, {create_date}, {title}, {200 - i}, {origin_content}, {origin_title}, {url}, {web_site})")

### h2 database close ###
curs.close()
conn.close()
