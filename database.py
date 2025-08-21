from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker, declarative_base

# DB Credentials
DB_USER = "admin"
DB_PASS = "11556"
DB_HOST = "localhost"
DB_PORT = "3306"
DB_NAME = "online_shop_ecommerce"

DATABASE_URL = f"mysql+pymysql://{DB_USER}:{DB_PASS}@{DB_HOST}:{DB_PORT}/{DB_NAME}"

# SQLAlchemy engine & session
engine = create_engine(DATABASE_URL, echo=True, future=True)
SessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=engine)

Base = declarative_base()

# Dependency for DB session
def get_db():
    db = SessionLocal()
    try:
        yield db
    finally:
        db.close()
