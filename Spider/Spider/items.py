# -*- coding: utf-8 -*-

# Define here the models for your scraped items
#
# See documentation in:
# https://docs.scrapy.org/en/latest/topics/items.html

import scrapy


class SpiderItem(scrapy.Item):
    # define the fields for your item here like:
    # name = scrapy.Field()
    pass

class JobInfoItem(scrapy.Item):

    job_id = scrapy.Field()
    job_title = scrapy.Field()
    company = scrapy.Field()
    work_place = scrapy.Field()
    job_pay = scrapy.Field()
    release_time = scrapy.Field()
    job_url = scrapy.Field()
    company_url  = scrapy.Field()
    job_state = scrapy.Field()

    pass