import tweepy
import db_connect

class Trends():
    # Consumer keys and access tokens, used for OAuth
    consumer_key = '73EMpmOO8grburFKzdczZcBfn'
    consumer_secret = 'Z96em1dgd8dC9FIEhmx403XHlEkHT7hZ5WJmLNTiAZ1m2nWxtA'
    access_token = '1887434144-EVLqulCFhJYLYMty7Cn2KUM2IuFdLtgwI6AKlKD'
    access_token_secret = '6MZoH56OTBYk6ogPzVFCJqC19kMMj4JdGIQpzhreIokTf'
    # OAuth process, using the keys and tokens
    def auth_object(self):
        auth = tweepy.OAuthHandler(self.consumer_key, self.consumer_secret)
        auth.set_access_token(self.access_token, self.access_token_secret)
        return auth

    def newyork_trends(self):
        trends_list = []
        # Creation of the actual interface, using authentication
        api = tweepy.API(self.auth_object())
        trends = api.trends_place(2459115)
        top_trends = trends[0]['trends']
        for i in top_trends:
            word = i["query"]
            for j in word:
                if j in '%23':
                    word = word.replace(j, "")
                elif j in "+":
                    word = word.replace(j, " ")
            trends_list.append(word)
        return trends_list


    def tweets_trends(self, list_trends):
        api = tweepy.API(self.auth_object())
        dic_tweets = {}
        tweets_list = []
        for i in list_trends:
            search_result = api.search(q=i, count=15)
            for j in search_result:
                tweets_list.append(j.text)
            dic_tweets[i] = tweets_list
            tweets_list = []
        return dic_tweets


    def trends_save_db(self, list_trends):
        db,cursor=self.return_cursor()
        cursor.execute("DROP TABLE IF EXISTS LINKS_TREND")
        cursor.execute("DROP TABLE IF EXISTS TWEETS_TREND")
        cursor.execute("DROP TABLE IF EXISTS TRENDS")
        sql="""CREATE TABLE IF NOT EXISTS TRENDS(
               trend_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
               trend varchar(40) NOT NULL 
               )ENGINE=InnoDB"""
        cursor.execute(sql)
        for i in list_trends:
            sql="""INSERT INTO TRENDS(trend)
               VALUES(%s)"""

            try:
                cursor.execute(sql,(i))
                db.commit()
            except:
                db.rollback()
        db.close()
        return 1
    

    def tweets_links_save_db(self,dic_tweets_links,table_name,name_id,name_string):
        db,cursor=self.return_cursor()
        sql="CREATE TABLE IF NOT EXISTS "+table_name+"("+name_id+" int NOT NULL AUTO_INCREMENT PRIMARY KEY,trend_id int NOT NULL," +name_string+ " varchar(150) NOT NULL,FOREIGN KEY fk_trend(trend_id) REFERENCES TRENDS(trend_id) )ENGINE=InnoDB"
        cursor.execute(sql)

        for key,value in dic_tweets_links.items():
            sql=""" SELECT trend_id 
                    FROM TRENDS 
                    WHERE trend='%s'"""%(key)
            cursor.execute(sql)
            results=cursor.fetchall()
            for rows in results:
                i=rows[0]
                for j in value:
                    sql=""" INSERT INTO """+table_name+""" (trend_id,"""+name_string+""")
                    VALUES(%s,%s)"""
                    try:
                        cursor.execute(sql,(i,j))
                        db.commit()
                    except:
                        db.rollback()
        db.close()
        return 1

 

    def return_cursor(self):
        db_object=db_connect.DbAccess()
        db=db_object.mysql_connect()
        cursor=db.cursor()
        return db,cursor






