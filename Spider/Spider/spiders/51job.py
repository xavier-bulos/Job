import re
import scrapy
from scrapy.http import Request
from Spider.items import JobInfoItem
from urllib import parse





class Job51Spider(scrapy.Spider):
    name =  "51job"
    allowed_domains = ["51job.com"]
    start_urls = ['https://search.51job.com/list/030000,000000,0100%252C7700%252C7200%252C7500%252C2700,01%252C37,9,99,%2520,2,1.html?lang=c&stype=&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&providesalary=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=']

    is_start = 1
    count = 1

    def parse(self, response):
        """
        1.获取搜索列表的url交给函数解析信息
        2.获取下一页的url
        :param response:
        :return:
        """

        url_list = response.css("p.t1 span a::attr(href)").getall()
        for job_url in url_list:
            yield Request(url=parse.urljoin(response.url,job_url), callback=self.parse_detail)

        #time = response.xpath('//*[@id="resultList"]/div[4]/span[4]/text()').get()
        #math_re = re.match(".*-(\d+).*", job_url)
        #time = int(math_re.group(1))
        #if time < 24:
        #    return

        if self.is_start == 1:
            joburl_next = response.css(".p_in .bk a::attr(href)").get().strip()
            self.is_start = 0
            yield Request(url=joburl_next, callback=self.parse)
        else:
            nextUrl_list = response.css(".p_in .bk a::attr(href)").getall()
            if len(nextUrl_list) == 2:
                if self.count > 200:
                    pass
                else:
                    self.count += 1
                    yield Request(url=nextUrl_list[1].strip(), callback=self.parse)

        pass


    def parse_detail(self,response):

        item = JobInfoItem()
        # response.css("p.t1 span a::text").extract()爬到的是列表

        job_url = response.url

        math_re = re.match(".*com/(.*).html.*", job_url)

        list = response.css(".cn .msg::text").getall()

        n = len(list)

        if n >= 6:
            time = list[4].strip()
        else:
            n = n - 1
            time = response.css(".cn .msg::text").getall()[n].strip()

        state = list[1].strip()


        item['job_title'] = response.css(".cn h1::text").get().strip()
        item['company'] = response.css(".cn .cname a::text").get().strip()
        item['work_place'] = response.css(".cn .msg::text").get().strip()
        item['job_pay'] = response.css(".cn strong::text").get().strip()
        item['release_time'] = time
        item['job_url'] = response.url
        item['company_url'] = response.css(".cn .cname a::attr(href)").get().strip()
        item['job_state'] = state
        item["job_id"] = math_re.group(1)



        yield item