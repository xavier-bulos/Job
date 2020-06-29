# -*- coding: utf-8 -*-

# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://docs.scrapy.org/en/latest/topics/item-pipeline.html

import MySQLdb
from twisted.enterprise import  adbapi
import  MySQLdb.cursors

class SpiderPipeline:
    def process_item(self, item, spider):
        return item


class MysqlPipeline(object):
    def __init__(self):
        self.conn = MySQLdb.connect('localhost','root','123456','creeper',charset="utf8",use_unicode=True)
        self.cursor = self.conn.cursor()


    def process_item(self,item,spider):
        insert_sql = """
            insert into job(job_id,job_title,company,work_place,job_pay,release_time,job_url,company_url,job_state)
            VALUES(%s,%s,%s,%s,%s,%s,%s,%s,%s)
        """
        self.cursor.execute(insert_sql, (item["job_id"],item["job_title"],item["company"],item["work_place"],
                                         item["job_pay"],item["release_time"],item["job_url"],item["company_url"],item["job_state"],))
        self.conn.commit()


class MysqlTwistedPipeline(object):
    def __init__(self,dbpool):
        self.dbpool = dbpool

    @classmethod
    def from_settings(cls,settings):
        dbparms = dict(
            host = settings["MYSQL_HOST"],
            db = settings["MYSQL_DBNAME"],
            user = settings["MYSQL_USER"],
            passwd = settings["MYSQL_PASSWORD"],
            charset='utf8',
            cursorclass=MySQLdb.cursors.DictCursor,
            use_unicode = True,
        )

        dbpool = adbapi.ConnectionPool("MySQLdb", **dbparms)

        return  cls(dbpool)

    def process_item(self, item, spider):
        #异步
        query = self.dbpool.runInteraction(self.do_insert, item)
        query.addErrback(self.handle_error)

    def handle_error(self,failure):
        print(failure)

    def do_insert(self, cursor, item):
        insert_sql = """
                    insert into job(job_id,job_title,company,work_place,job_pay,release_time,job_url,company_url,job_state)
                    VALUES(%s,%s,%s,%s,%s,%s,%s,%s,%s)
                """
        cursor.execute(insert_sql, (item["job_id"], item["job_title"], item["company"], item["work_place"],
                                         item["job_pay"], item["release_time"], item["job_url"], item["company_url"],
                                         item["job_state"],))
