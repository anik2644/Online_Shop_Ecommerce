from fastapi import FastAPI
from src.ecommerce.infrastructure.storage.db import Base, engine
from src.ecommerce.controller.users import auth_controller
from src.ecommerce.controller.products.products import router as product_router


# create tables only if you want auto-sync for dev
# Base.metadata.create_all(bind=engine)

app = FastAPI(title="ecommerce api")

app.include_router(auth_controller.router)
app.include_router(product_router)

@app.get("/health")
def health():
    return {"status": "ok"}
