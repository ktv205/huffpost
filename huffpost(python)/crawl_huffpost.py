
from bs4 import BeautifulSoup
import urllib
import mechanize

class CrawlHuffpost():
	
	def initialize_variables(self,trend_list,seed):
		htmlfile=urllib2.urlopen(seed)
		soup=BeautifulSoup(htmlfile.read())
		for i in soup.find_all("a"):
			print i.get("href")
		

		
			

		


	def crawl_page(self):
		pass

	def save_links_page(self,soup):
		print("hello")

	def search_page_trend(send):
		pass

