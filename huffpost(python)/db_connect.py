import MySQLdb


class DbAccess():
    cnx = {'host': '',
           'username': '',
           'password': '',
           'db': 'tweets'}

    def mysql_connect(self):
        db = MySQLdb.connect(self.cnx['host'], self.cnx['username'], self.cnx['password'], self.cnx['db'])
        return db
