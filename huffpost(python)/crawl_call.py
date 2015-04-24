import google_search_huffpost

def main():
    crawl_object=google_search_huffpost.GoogleSearchHuffPost()
    crawl_object.search_trend_huffpost("chris pratt")

if __name__ == '__main__':
	main()
