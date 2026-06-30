from sqlalchemy.orm import Session

from . import models, schemas

from .models import Product


# Registrar usuario
def create_user(db: Session, user: schemas.UserCreate):

    existing = db.query(models.User).filter(
        models.User.email == user.email
    ).first()

    if existing:
        return None

    new_user = models.User(
        nombre=user.nombre,
        email=user.email,
        password=user.password
    )

    db.add(new_user)
    db.commit()
    db.refresh(new_user)

    return new_user


# Login
def login(db: Session, user: schemas.UserLogin):

    return db.query(models.User).filter(
        models.User.email == user.email,
        models.User.password == user.password
    ).first()


def seed_products(db: Session):

    if db.query(Product).count() > 0:
        return

    products = [
        Product(
            title="Fender Stratocaster",
            description="Guitarra eléctrica profesional",
            price=1200,
            image="https://images.unsplash.com/photo-1606041281659-3f2cec516ac0?q=80&w=688&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            category="Guitarras"
        ),
        Product(
            title="Yamaha P-145",
            description="Piano digital",
            price=850,  
            image="https://images.unsplash.com/photo-1552422535-c45813c61732?q=80&w=600&auto=format&fit=crop",
            category="Pianos"
        ),
        Product(
            title="Ibanez RG550",
            description="Guitarra eléctrica de alto rendimiento para rock y metal.",
            price=1350,
            image="https://images.unsplash.com/photo-1510915361894-db8b60106cb1?w=800",
            category="Guitarras"
        ),

        Product(
            title="Roland TD-02K",
            description="Batería electrónica compacta ideal para practicar.",
            price=980,
            image="https://images.unsplash.com/photo-1519892300165-cb5542fb47c7?w=800",
            category="Baterías"
        ),

        Product(
            title="Pearl Export Drum Set",
            description="Batería acústica de 5 piezas con platillos.",
            price=1750,
            image="https://images.unsplash.com/photo-1493225457124-a3eb161ffa5f?w=800",
            category="Baterías"
        ),

        Product(
            title="Yamaha TRBX174",
            description="Bajo eléctrico de excelente calidad para principiantes.",
            price=620,
            image="https://images.unsplash.com/photo-1507838153414-b4b713384a76?w=800",
            category="Bajos"
        ),

        Product(
            title="Casio CT-S300",
            description="Teclado portátil de 61 teclas con múltiples sonidos.",
            price=340,
            image="https://images.unsplash.com/photo-1514119412350-e174d90d280e?w=800",
            category="Teclados"
        ),

        Product(
            title="Shure SM58",
            description="Micrófono dinámico profesional para voz.",
            price=110,
            image="https://images.unsplash.com/photo-1516280440614-37939bbacd81?w=800",
            category="Micrófonos"
        ),

        Product(
            title="Audio-Technica ATH-M50X",
            description="Audífonos profesionales para monitoreo de estudio.",
            price=190,
            image="https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=800",
            category="Accesorios"
        ),

        Product(
            title="Marshall MG30GFX",
            description="Amplificador de guitarra de 30W con efectos integrados.",
            price=420,
            image="https://images.unsplash.com/photo-1511379938547-c1f69419868d?w=800",
            category="Amplificadores"
        )
    ]

    db.add_all(products)

    db.commit()