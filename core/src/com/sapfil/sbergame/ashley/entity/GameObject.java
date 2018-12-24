package com.sapfil.sbergame.ashley.entity;//package com.sapfil.q1.ashley.entity;
//
//import com.badlogic.ashley.core.Entity;
//import com.sapfil.q1.Objects.GFXObjects.AGFXO;
//import com.sapfil.q1.Objects.GameObjects.GameObjectsCollection;
//import com.sapfil.q1.Objects.ObjectDependency.PositionDependency;
//import com.sapfil.q1.Objects.PhysicsObjects.HealthObject;
//import com.sapfil.q1.Objects.PhysicsObjects.IHealthObject;
//import com.sapfil.q1.Objects.Position.Coordinates;
//import com.sapfil.q1.Objects.specialObjects.Base;
//import com.sapfil.q1.Objects.specialObjects.Hero;
//import com.sapfil.q1.States.Layer;
//import com.sapfil.q1.XML.DataBaseFromXML;
//import com.sapfil.q1.ashley.components.PositionComponent;
//
//import static com.sapfil.q1.GlobalConstants.GFX_TO_PHYS;
//
///************************************************
// * Created by Sapfil on 26.02.2017.
// * Last edit: 26.03.2017 Complex Refactor
// * Notes:
// ***********************************************/
//public class GameObject extends Entity{
//
//    // ===========================================================
//    // Constants and static fields
//    // ===========================================================
//
//    // ===========================================================
//    // Fields
//    // ===========================================================
//
//    private int id;
//    private int typeOfObject;
//
//    private GameObjectsCollection gameObjectsCollection;
//
//    // ===========================================================
//    // Constructors
//    // ===========================================================
//
//    // ===========================================================
//    // Getter & Setter
//    // ===========================================================
//
//    // Global parameters
//    public PositionComponent getLocalCoordinates() {
//        return this.getComponent(PositionComponent.class);
//    }
//    public void setLocalCoordinates(PositionComponent positionComponent) {
//        this.remove(PositionComponent.class);
//        this.add(positionComponent);
//    }
//
//    public Coordinates getCoordinatesCorrection() {
//        return coordinatesCorrection;
//    }
//    public void setCoordinatesCorrection(Coordinates coordinates){
//        coordinatesCorrection = coordinates;
//    }
//
//    // Plugins
//    public IPosition getEntity() {
//        return entity;
//    }
//    public AGFXO getGfxObject() {
//        return gfxObject;
//    }
//    public PositionDependency getDependencyObject() {return  positionDependency;}
//    public IHealthObject getHealthObject(){return healthObject;}
//    public Hero getHeroProperty(){return heroProperty;}
//    public Base getBaseProperty() {return baseProperty;}
//    public void setBaseProperty(Base base) {baseProperty = base; }
//
//    //shortcut-getters
//    public int getID(){ return id;}
//    public int getTypeOfObject() {return typeOfObject;}
//    public float getAbsX(){return (localCoordinates.getX() + coordinatesCorrection.getX());}
//    public float getAbsY(){
//        return localCoordinates.getY() + coordinatesCorrection.getY();
//    }
//    public float getAbsRotation(){return (localCoordinates.getRotation() + coordinatesCorrection.getRotation());}
//
//    // ===========================================================
//    // Methods for/from SuperClass/Interfaces (U/R/D)
//    // ===========================================================
//
////    @Override
////    public void changeGfxObject( newGfxObject) {
////        AGFXO = newGfxObject;
////    }
//    @Override
//    public void dispose(){
//        this.dispose(false);
//    }
//
//    @Override
//    public void dispose(boolean createBlast) {
//        if (healthObject != null) {
//            layer.getHealthObjectCollection().deleteWholeHx(healthObject, typeOfObject);
//            if (createBlast && gameObjectsCollection != null && sDB.healthPartMap.get(id).type_of_blast != 0)
//                new GameObject.GameObjectBuilder()
//                        .create(sDB.healthPartMap.get(id).type_of_blast, layer)
//                        .withXYcoords(this.getAbsX(), this.getAbsY(), true)
//                        .insertToCollection(gameObjectsCollection)
//                        .build();
//            healthObject = null;
//        }
//        if (positionDependency != null)
//            layer.getChildrenCollection().deleteWholeHx(positionDependency);
//        if (gfxObject != null) {
//            layer.getzSortedGFXCollecion().deleteWholeHx(gfxObject);
//        }
//        if (entity != null)
//            layer.getLocalPositionCollection().unregister(entity);
//
//        // If delete this - will be memory leak by duplicating recreated objects
//        if (gameObjectsCollection != null)
//            gameObjectsCollection.deleteWholeHx(this);
//
//        localCoordinates = null;
//        coordinatesCorrection = null;
//    }
//
//    // ===========================================================
//    // Methods
//    // ===========================================================
//
//    // ===========================================================
//    // Inner and Anonymous Classes
//    // ===========================================================
//
//    public static class GameObjectBuilder {
//
//        private static GameObject gameObject;
//        private PositionUpdater.EntityBuilder entityBuilder;
//        private boolean specialEntityOriginSet = false;
//        private boolean xyRecalculationByGFXcenter = true;
//        private GameObject parent = null;
//
//        public GameObjectBuilder create(int id, Layer layer){
//            gameObject = new GameObject();
//            entityBuilder = new PositionUpdater.EntityBuilder().createEntity(gameObject);
//            entityBuilder.entityID(id);
//            gameObject.id = id;
//            gameObject.typeOfObject = sDB.basicPartMap.get(id).type_of_object;
//            gameObject.layer = layer;
//            return this;
//        }
//
//        // collection
//        public GameObjectBuilder insertToCollection(GameObjectsCollection gameObjectsCollection){ gameObject.gameObjectsCollection = gameObjectsCollection; return this;}
//
//        // global parameters
//        public GameObjectBuilder withRotation(float pRotation)              {gameObject.getLocalCoordinates().setRotation(pRotation); return this;}
//        public GameObjectBuilder withXYcoords(float x, float y)             {return this.withXYcoords(x,y, false);}
//        public GameObjectBuilder withXYcornerCoords(float x, float y)       {gameObject.getLocalCoordinates().setX(x); gameObject.getLocalCoordinates().setY(y);  xyRecalculationByGFXcenter = false; return this;}
//        public GameObjectBuilder withZcoord(float z)                        {gameObject.getLocalCoordinates().setZ(z);return  this;}
//
//        // entity parameters
//        public GameObjectBuilder withID(int ID)                             {entityBuilder.entityID(ID); gameObject.id = ID; return this;}
//        public GameObjectBuilder withType(int type)                         {entityBuilder.entityType(type); return this;}
//        public GameObjectBuilder withScale(float sX, float sY)              {entityBuilder.entityScale(sX, sY); return this;}
//        public GameObjectBuilder withSpeedXY(float pVX, float pVY)          {return this.withSpeedXY(pVX,pVY, false);}
//        public GameObjectBuilder withSpeedV(float pV)                       {entityBuilder.entitySpeedV(pV); return this;}
//        public GameObjectBuilder withRotationSpeed(float pRotaionSpeed)     {entityBuilder.entityRotationSpeed(pRotaionSpeed); return this;}
//        public GameObjectBuilder withOriginXY(float originX, float originY) {return this.withOriginXY(originX,originY,false);}
//
//        public GameObjectBuilder withSpeedXY(float x, float y, boolean realCoords) {
//            if (realCoords)entityBuilder.entitySpeedXY(x, y);
//            else entityBuilder.entitySpeedXY(x * GFX_TO_PHYS, y * GFX_TO_PHYS);
//            return this;
//        }
//        public GameObjectBuilder withXYcoords(float x, float y, boolean realCoords) {
//            if (realCoords){gameObject.getLocalCoordinates().setX(x); gameObject.getLocalCoordinates().setY(y);}
//            else {gameObject.getLocalCoordinates().setX(x * GFX_TO_PHYS); gameObject.getLocalCoordinates().setY(y * GFX_TO_PHYS);}
//            return this;
//        }
//        public GameObjectBuilder withOriginXY(float originX, float originY, boolean realCoords) {
//            if (realCoords) entityBuilder.entityOriginXY(originX, originY);
//            else entityBuilder.entityOriginXY(originX * GFX_TO_PHYS, originY * GFX_TO_PHYS);
//            specialEntityOriginSet = true;
//            return this;
//        }
//
//        //dependency parameters
//        public GameObjectBuilder setParent(GameObject parent)              {this.parent = parent; return this;}
//
//        public GameObject build() {
//
//
//            // entity build-start
//            gameObject.entity = entityBuilder.build();
//
//            if (!specialEntityOriginSet)
//                gameObject.entity.setOriginXY(sDB.basicPartMap.get(gameObject.getID()).origin_x * GFX_TO_PHYS,
//                        sDB.basicPartMap.get(gameObject.getID()).origin_y * GFX_TO_PHYS);
//
//            // dependency build
//            if (parent != null) {
//                gameObject.positionDependency = new PositionDependency(parent, gameObject);
//            }
//
//            // body build
//            if (sDB.healthPartMap.get(gameObject.id) != null) {
//                if (parent == null)
//                    gameObject.healthObject = new HealthObject(gameObject);
//                else
//                    gameObject.healthObject = new HealthObject(gameObject, parent);
//            }
//
//            //TODO withoutGFX
//            // gfx build and entity build-end (after dependancy and HO)
//            gameObject.gfxObject = new AGFXO.GfxObjectBuilder()
//                    .createGFxObjectEntity(gameObject.id, gameObject)
//                    .gfxZcoord(gameObject.getLocalCoordinates().getZ())
//                    .build();
//
//            if (!xyRecalculationByGFXcenter) {
//                gameObject.getLocalCoordinates().setX(gameObject.getLocalCoordinates().getX() + gameObject.getGfxObject().getGfxCenterX());
//                gameObject.getLocalCoordinates().setY(gameObject.getLocalCoordinates().getY() + gameObject.getGfxObject().getGfxCenterY());
//            }
//
//            // special build
//            if (gameObject.id == 1)
//                gameObject.heroProperty = new Hero(gameObject);
//            if (gameObject.id == 800)
//                gameObject.baseProperty = new Base(gameObject);
//
//            // entity registration - not need to update coordinates if Body exists.
//            if (sDB.healthPartMap.get(gameObject.id) == null) {
//                gameObject.layer.getLocalPositionCollection().register(gameObject.entity);
//            }
//
//            // gfx registration
//                gameObject.layer.getzSortedGFXCollecion().addNewObject(gameObject.gfxObject);
//            //dependency registration
//            if (gameObject.positionDependency != null) {
//                gameObject.layer.getChildrenCollection().addNewObject(gameObject.positionDependency);
//                //base components
//                if (gameObject.getTypeOfObject() == 800) {
//                    while (parent.getDependencyObject() != null)
//                        parent = parent.getDependencyObject().getParent();
//                    gameObject.baseProperty = parent.getBaseProperty();
//                    if (gameObject.getID() == 812)
//                        gameObject.baseProperty.setEntrancePosint(gameObject);
//                    if (gameObject.getID() == 810)
//                        gameObject.baseProperty.setDockPoint(gameObject);
//                }
//            }
//            // body registration
//            if (gameObject.healthObject != null)
//                gameObject.layer.getHealthObjectCollection().addNewObject(gameObject.healthObject, gameObject.typeOfObject);
//
//            if (gameObject.gameObjectsCollection != null)
//                gameObject.gameObjectsCollection.addNewObject(gameObject);
//
//            // TOTAL BUILD COMPLETE
//            return gameObject;
//        }
//    }
//}