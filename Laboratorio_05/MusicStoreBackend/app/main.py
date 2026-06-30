from fastapi import FastAPI

from .database import Base, engine

from .routers import users
from .routers import products

Base.metadata.create_all(bind=engine)

from .database import SessionLocal
from .crud import seed_products

db = SessionLocal()

seed_products(db)

db.close()

app = FastAPI(
    title="Music Store API",
    description="API REST para la aplicación Music Store",
    version="1.0.0"
)

app.include_router(users.router)
app.include_router(products.router)

@app.get("/")
def root():
    return {
        "message": "Bienvenido a Music Store API"
    }