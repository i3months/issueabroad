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

dtype = "'f r e e'"
comment_cnt = "0"
content = "' expected [, ::, AT, "" ""FORMAT, *, /, %, +, -, ||, ~, !~, NOT, LIKE, ILIKE, REGEXP, IS, IN, BETWEEN, AND, OR, ,, ); SQL statement:자유게시판 내용 112 3 1 2 3 4 12 3 13512'"
create_date = "20221130"
title = "'자유게시판제목"
view_cnt = "0"
origin_content = "'none'"
origin_title = "'none'"
url = "'none'"
web_site = "'none'"

for i in range(1, 2):
    title = "'제목입니다."
    title = title + str(i) + "'"    
    curs.execute(f"insert into article (dtype, comment_count, content, create_date, title, view_count, origin_content, origin_title, url, web_site) values ({dtype}, {comment_cnt}, {content}, {create_date}, {title}, {view_cnt}, {origin_content}, {origin_title}, {url}, {web_site})")

### h2 database close ###
curs.close()
conn.close()
