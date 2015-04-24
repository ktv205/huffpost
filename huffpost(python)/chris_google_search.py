import urllib
import mechanize
from bs4 import BeautifulSoup
import re

br=mechanize.Browser()
br.set_handle_robots(False)
br.addheaders=[('User-agent','chrome')]
term="".replace(" ","+")
query="http://www.google.com/search?num=4&site=&source=hp&q=huffington+post+akshay+kumar"
htmltext=br.open(query).read()
soup=BeautifulSoup(htmltext)
search=soup.find_all('div',attrs={'id':'search'})
soup1=BeautifulSoup(str(search))
regex="q(?!.*q).*?&amp"
pattern=re.compile(regex)
url_list=soup1.find_all('li')
for li in range(len(url_list)):
	soup2=BeautifulSoup(str(url_list[li]))
	links=soup2.find_all('a')
	link_source=str(links[0])
	if(link_source.find("webcache")==-1):
		start_point=link_source.find("http")
		end_point=link_source.find("&amp")
		print(link_source[start_point:end_point])