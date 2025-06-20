from fastapi import FastAPI, Request
from pydantic import BaseModel
from openai import OpenAI
import os

app = FastAPI()

api_key = "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"  # 또는 경고 출력
client = OpenAI(api_key=api_key)

class UserInput(BaseModel):
    taste: str            # "sweet", "sour", "astringent", "bitter", "umami"
    price: int            # 1 ~ 5
    heavy: bool
    sparkling: bool
    alcohol_level: int    # 1 ~ 4

@app.post("/recommend")
async def recommend_liquor(data: UserInput):
    prompt = f"""
고객이 다음 조건을 선택했습니다.  
- 맛: {data.taste}  
- 가격대: {data.price}  
- 무게감: {"무거운" if data.heavy else "가벼운"}  
- 탄산감: {"있음" if data.sparkling else "없음"}  
- 도수: {data.alcohol_level} 구간  

위 조건에 모두 맞는 술 또는 칵테일, 하이볼, 편의점 조합술 레시피를 추천해 주세요.  

반환 형식은 Kotlin 데이터 클래스 `Liquor` 형태로 아래와 같이 작성해 주세요:

val liquor = Liquor(
    name = "술 이름",
    description = "술 또는 레시피 설명을 한두 줄로 요약",
    image_url = "URL",
    liquor_type = "Cocktail",  // 예시
    price_range = {data.price},
    sweet = {str(data.taste == "sweet").lower()},
    sour = {str(data.taste == "sour").lower()},
    astringent = {str(data.taste == "astringent").lower()},
    bitter = {str(data.taste == "bitter").lower()},
    umami = {str(data.taste == "umami").lower()},
    heavy = {str(data.heavy).lower()},
    sparkling = {str(data.sparkling).lower()},
    alcohol_level = {data.alcohol_level}
).let {{
    it.copy(search_tokens = generateSearchTokens(it.name))
}}
"""

    completion = client.chat.completions.create(
        model="gpt-4o",
        messages=[{"role": "user", "content": prompt}]
    )

    response = completion.choices[0].message.content
    return {"recommendation": response}
