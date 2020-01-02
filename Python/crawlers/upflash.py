import requests
from bs4 import BeautifulSoup as bs
import os, time

class upFlash:
    def __init__(self):
        self.headers = {"User-Agent": "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36"}
        self.url = "https://unsplash.com"

    def request(self, url):
        re = requests.get(url, headers=self.headers)
        return re
    
    def mkdir(self, path):
        if not os.path.exists(path):
            os.makedirs(path)
    
    def save_img(self, url, name):
        print("crawler starts")
        img = self.request(url)
        
        time.sleep(5)  #wait for loading the web content

        file_name = name + '.jpg'
        print(file_name)
        f = open(file_name, 'ab')
        f.write(img.content)
        print("finish")
        f.close()
    
    def get_pic(self):
        re = self.request(self.url)
        soup = bs(re.content, 'lxml').find_all('img', class_="_2zEKz")

        self.mkdir("/Users/chaozy/Desktop/CS/pythonReptile/imgFromUpflash")
        os.chdir("/Users/chaozy/Desktop/CS/pythonReptile/imgFromUpflash")

        index = 1
        for s in soup:
            try:
                img_str = s['srcset']
                img_url = img_str[img_str.index("100w, ") + 6 : img_str.index("200w, ")]
                print(img_url)
                self.save_img(img_url, str(index))
                index += 1
                #print(img_str[img_str.index("100w, ") + 6 : img_str.index("200w, ")])
            except:
                print("no found url")

crawler = upFlash()
crawler.get_pic()