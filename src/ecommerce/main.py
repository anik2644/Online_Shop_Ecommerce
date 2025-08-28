from fastapi import FastAPI
from starlette.middleware.cors import CORSMiddleware

from src.ecommerce.infrastructure.storage.db import Base, engine
from src.ecommerce.controller.users import auth_controller
from src.ecommerce.controller.products.products import router as product_router


# create tables only if you want auto-sync for dev
# Base.metadata.create_all(bind=engine)

app = FastAPI(title="ecommerce api")

app.include_router(auth_controller.router)
app.include_router(product_router)


# Allow Swagger UI and frontend to reach your backend
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],  # or restrict to ["http://localhost:8000", "http://127.0.0.1:8000"]
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

@app.get("/health")
def health():
    return {"status": "ok"}
