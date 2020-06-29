__author__=""

from scrapy.cmdline import execute

import sys
import os

#sys.path.append("E:\scrapy project\Spider")
sys.path.append(os.path.dirname(os.path.abspath(__file__)))
execute(["scrapy","crawl","51job"])
#传递数组，运行命令行（数组里的内容）
