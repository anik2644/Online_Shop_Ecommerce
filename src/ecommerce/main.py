from fastapi import FastAPI
from src.ecommerce.infrastructure.storage.db import Base, engine
from src.ecommerce.controller.users import auth_controller

# create tables only if you want auto-sync for dev
# Base.metadata.create_all(bind=engine)

app = FastAPI(title="ecommerce api")

app.include_router(auth_controller.router)

@app.get("/health")
def health():
    return {"status": "ok"}
