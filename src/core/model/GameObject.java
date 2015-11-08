package core.model;

public abstract class GameObject implements TestInf {

	private int id;


	protected int x;
	protected int y;
	private boolean draw, update;

    public GameObject(int id) {
        this.id = id;
    }
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}

	public boolean isDraw() {
		return draw;
	}

	public void setDraw(boolean draw) {
		this.draw = draw;
	}

	public boolean isUpdate() {
		return update;
	}

	public void setUpdate(boolean update) {
		this.update = update;
	}


    public boolean hasId(int id) {
        return id == this.id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void who() {
        System.out.println("I'm core.model, my id: #"+ getId());
    }
}
