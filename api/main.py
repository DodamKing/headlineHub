from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware
from pydantic import BaseModel
import requests
from bs4 import BeautifulSoup

app = FastAPI()

# cors 설정
app.add_middleware(
    CORSMiddleware,
    allow_origins=['http://localhost:8080', 'http://127.0.0.1:8080'],
    allow_credentials=True,
    allow_methods=["*"],  # 모든 HTTP 메소드 허용
    allow_headers=["*"],  # 모든 헤더 허용
)

class DataInput(BaseModel):
    name: str

@app.get('/')
def index():
    return {'Hello': 'World'}

@app.get('/test')
def api_test():
    return test_f()

@app.post('/swg')
async def swagger_test(msg: str, data: DataInput):
    res = {'hello': 'world', 'hi': 'world', 'msg': msg, 'name': data.name}
    return res

@app.get('/get-headline')
def get_headline():
    naver = get_headline_naver()
    daum = get_headline_daum()
    if daum:
        for i in daum: naver.append(i)

    return {'headlines': naver}

@app.get('/search-news')
def search_news(keyword: str):
    url = 'https://search.naver.com/search.naver?where=news&ie=utf8&sm=nws_hty&query='
    url += keyword

    response = requests.get(url)
    soup = BeautifulSoup(response.text, 'html.parser')
    target_div = soup.find_all('div', class_='news_contents')

    searchresults = []
    if target_div:
        for target in target_div:
            title = target.find('a', class_='news_tit').text
            link = target.find('a', class_='news_tit')['href'] if target.find('a', class_='news_tit') else ''
            img = target.find('img')['src'] if target.find('img') else ''
            headline = Headline(title, link, img)
            searchresults.append(headline)

    url = 'https://search.daum.net/search?w=tot&nil_profile=search&nil_src=media&DA=23A&rtmaxcoll=DNS%2CNNS&q='
    url += keyword

    response = requests.get(url)
    soup = BeautifulSoup(response.text, 'html.parser')

    targets = soup.find_all('div', class_='c-item-content')

    if targets:
        for target in targets:
            title = target.find('strong', class_='tit-g clamp-g').text
            link = target.find('a')['href']
            headline = Headline(title, link, '')
            searchresults.append(headline)

    return {'searchresults': searchresults}

def test_f():
    return True

class Headline:
    def __init__(self, title, link, img):
        self.title = title
        self.link = link
        self.img = img

def get_headline_naver():
    
    url = 'https://news.naver.com/main/officeList.naver'

    response = requests.get(url)
    soup = BeautifulSoup(response.text, 'html.parser')
    target_div = soup.find('div', class_='section _officeTopRanking1087479')

    if target_div: 
        target_ul = target_div.find('ul', id='_rankingList0')
        target_li_list = target_ul.find_all('li')

        headlines = []

        for li in target_li_list:
            title = li.find('a', class_='list_tit').text
            link = li.find('a')['href']
            img = li.find('img')['src']
            headline = Headline(title, link, img)
            headlines.append(headline)

        return headlines
    
    else: return False

def get_headline_daum():
    url = 'https://news.daum.net/breakingnews/'

    response = requests.get(url)
    soup = BeautifulSoup(response.text, 'html.parser')
    target_div = soup.find('div', class_='box_etc')
    target_ul = target_div.find('ul', class_='list_news2 list_allnews')
    target_li_list = target_ul.find_all('li')

    if target_li_list:
        headlines = []
        for target in target_li_list:
            title = target.find('a', class_='link_txt').text
            link = target.find('a', class_='link_txt')['href']
            img = target.find('img')['src'] if target.find('img') else ''
            headline = Headline(title, link, img)
            headlines.append(headline)
        return headlines
    else: return False