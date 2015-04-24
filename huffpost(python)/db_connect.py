import MySQLdb


class DbAccess():
    cnx = {'host': 'huffpost.cywgftwybpn7.us-east-1.rds.amazonaws.com',
           'username': 'krishna',
           'password': 'frontline',
           'db': 'tweets'}

    def mysql_connect(self):
        db = MySQLdb.connect(self.cnx['host'], self.cnx['username'], self.cnx['password'], self.cnx['db'])
        return db
