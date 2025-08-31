from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
from minio import Minio
import base64
import io
from datetime import timedelta

app = FastAPI()

# MinIO client (adjust endpoint if running remotely)
minio_client = Minio(
    endpoint="127.0.0.1:9000",
    access_key="admin",
    secret_key="1155615865",
    secure=False   # http not https
)

BUCKET = "test"

# Ensure bucket exists
if not minio_client.bucket_exists(BUCKET):
    minio_client.make_bucket(BUCKET)


# -------------------------
# Request models
# -------------------------
class UploadRequest(BaseModel):
    image_name: str
    image: str   # base64 string


class GetRequest(BaseModel):
    image_name: str




# -------------------------
# Upload API
# -------------------------
@app.post("/upload")
async def upload_image(data: UploadRequest):
    try:
        # Decode base64 image
        image_bytes = base64.b64decode(data.image)
        file_stream = io.BytesIO(image_bytes)

        # Upload to MinIO
        minio_client.put_object(
            bucket_name=BUCKET,
            object_name=data.image_name,
            data=file_stream,
            length=len(image_bytes),
            content_type="image/jpeg"
        )

        return {"status": "ok", "message": "Image uploaded successfully"}
    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))


# -------------------------
# Get Image API
# -------------------------

@app.post("/get-image")
async def get_image(req: GetRequest):
    try:
        # Confirm object exists
        minio_client.stat_object(BUCKET, req.image_name)

        # Generate presigned URL (valid for 1 hour)
        url = minio_client.presigned_get_object(
            BUCKET,
            req.image_name,
            expires=timedelta(hours=1)
        )

        return {
            "status": "ok",
            "image_name": req.image_name,
            "url": url
        }
    except Exception as e:
        raise HTTPException(
            status_code=404,
            detail=f"Image '{req.image_name}' not found: {str(e)}"
        )


# from fastapi import FastAPI
# from starlette.middleware.cors import CORSMiddleware
#
# from src.ecommerce.infrastructure.storage.db import Base, engine
# from src.ecommerce.controller.users import auth_controller
# from src.ecommerce.controller.products.products import router as product_router
#
#
# # create tables only if you want auto-sync for dev
# # Base.metadata.create_all(bind=engine)
#
# app = FastAPI(title="ecommerce api")
#
# app.include_router(auth_controller.router)
# app.include_router(product_router)
#
#
# # Allow Swagger UI and frontend to reach your backend
# app.add_middleware(
#     CORSMiddleware,
#     allow_origins=["*"],  # or restrict to ["http://localhost:8000", "http://127.0.0.1:8000"]
#     allow_credentials=True,
#     allow_methods=["*"],
#     allow_headers=["*"],
# )
#
# @app.get("/health")
# def health():
#     return {"status": "ok"}
