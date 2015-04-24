import urllib
import mechanize
from bs4 import BeautifulSoup
import db_connect

class GoogleSearchHuffPost():

	def search_trend_huffpost(self,trend):
		list_urls=[]
		br=mechanize.Browser()
		br.set_handle_robots(False)
		br.addheaders=[('User-agent','Firefox')]
		trend="huffington post "+trend
		trend=trend.replace(" ","+")
		query="http://www.google.com/search?num=10&site=&source=hp&q="+trend
		#print(query)
		htmltext=br.open(query).read()
		soup=BeautifulSoup(htmltext)
		search=soup.find_all('div',attrs={'id':'search'})
		soup1=BeautifulSoup(str(search))
		url_list=soup1.find_all('li')
		for li in range(len(url_list)):
			soup2=BeautifulSoup(str(url_list[li]))
			links=soup2.find_all('a')
			link_source=str(links[0])
			if(link_source.find("webcache")==-1):
				start_point=link_source.find("http")
				end_point=link_source.find("&amp")
				list_urls.append(link_source[start_point:end_point])
		return list_urls
        


