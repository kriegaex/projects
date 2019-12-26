import requests
from bs4 import BeautifulSoup as bs

url = 'http://pythonjobs.github.io/'
r2 = requests.get(url, verify=False)
s = bs(r2.content, 'lxml')

pythonJobs = s.find_all('div', class_='job')
for job in pythonJobs:
    title = job.find('h1')
    print(title.text)
    company_location = job.find_all('span', class_='info')
    for i in company_location:
        company = i.find('i', class_='i-company')
        location = i.find('i', class_='i-globe')
        if location:
            print("location: ", i.text)
        elif company:
            print("company: ", i.text)
    detail = job.find('p', class_="detail")
    link = job.find('a')['href']
    print("Description: ", detail.text)
    print("Read More >>>",url + link)
    print()