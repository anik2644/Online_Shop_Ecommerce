from fastapi import HTTPException, status

def unauthorized(detail="Invalid credentials"):
    return HTTPException(status_code=status.HTTP_401_UNAUTHORIZED, detail=detail)

def forbidden(detail="Forbidden"):
    return HTTPException(status_code=status.HTTP_403_FORBIDDEN, detail=detail)

def not_found(detail="Not found"):
    return HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail=detail)

def conflict(detail="Conflict"):
    return HTTPException(status_code=status.HTTP_409_CONFLICT, detail=detail)
