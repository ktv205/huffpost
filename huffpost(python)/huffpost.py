__author__ = 'krishnateja'

import trends
import google_search_huffpost

def main():
    trends_object = trends.Trends()
    list_trends = trends_object.newyork_trends()
    dic_tweets = trends_object.tweets_trends(list_trends)
    trends_object.trends_save_db(list_trends)
    trends_object.tweets_links_save_db(dic_tweets,"TWEETS_TREND","tweet_id","tweet")
    crawl_object=google_search_huffpost.GoogleSearchHuffPost()
    dic_links={}
    for i in list_trends:
    	list_urls=crawl_object.search_trend_huffpost(i)
    	dic_links[i]=list_urls
    trends_object.tweets_links_save_db(dic_links,"LINKS_TREND","link_id","link")
    



if __name__ == "__main__":
    main()
