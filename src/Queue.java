import Entities.PlaceOfInterest;

// Una clase para representar una queue
class Queue  {
    private PlaceOfInterest[] arr;      // array para almacenar elementos de la queue
    private int front;      // front apunta al elemento frontal en la queue
    private int rear;       // la parte trasera apunta al último elemento de la queue
    private int capacity;   // capacidad máxima de la queue
    private int count;      // tamaño actual de la queue

    // Constructor para inicializar una queue
    Queue() {
        arr = new PlaceOfInterest[10];
        capacity = 10;
        front = 0;
        rear = -1;
        count = 0;
    }

    // Función de utilidad para sacar de la queue el elemento frontal
    private PlaceOfInterest dequeue()
    {
        // comprobar si hay subdesbordamiento de la queue
        if (isEmpty())
        {
            System.out.println("Underflow\nProgram Terminated");
            System.exit(-1);
        } else {
            front++;
            if(front == capacity-1){
                System.out.println("Removed element: "+arr[front-1]);
                front = 0;
            } else {
                System.out.println("Removed element: "+arr[front-1]);
            }
            capacity--;
        }

        PlaceOfInterest x = arr[front];

        System.out.println("Removing " + x);

        front = (front + 1) % capacity;
        count--;

        return x;
    }

    // Función de utilidad para devolver el elemento frontal de la queue
    private PlaceOfInterest peek() {
        if (isEmpty()) {
            System.out.println("Underflow\nProgram Terminated");
            System.exit(-1);
        }
        return arr[front];
    }

    public PlaceOfInterest pull(){
        PlaceOfInterest x = peek();
        dequeue();
        return x;
    }

    // Función de utilidad para agregar un elemento a la queue
    public void add(PlaceOfInterest item)
    {
        // comprobar si hay desbordamiento de la queue
        if (isFull())
        {
            increaseCapacity();
        } else {
            rear++;
            if(rear == capacity-1){
                rear = 0;
            }
            arr[rear] = item;
            capacity++;
        }

        System.out.println("Inserting " + item);

        rear = (rear + 1) % capacity;
        arr[rear] = item;
        count++;
    }

    // Función de utilidad para devolver el tamaño de la queue
    public int size() {
        return count;
    }

    // Función de utilidad para verificar si la queue está vacía o no
    public boolean isEmpty() {
        return (size() == 0);
    }

    // Función de utilidad para verificar si la queue está llena o no
    public boolean isFull() {
        return (size() == capacity);
    }

    private void increaseCapacity(){
        //Create new array with double size as the current one.
        int newCapacity = this.arr.length*2;
        PlaceOfInterest[] newArr = new PlaceOfInterest[newCapacity];
        //Copy elements to new array
        int tmpFront = front;
        int index = -1;
        while(true){
            newArr[++index] = this.arr[tmpFront];
            tmpFront++;
            if(tmpFront == this.arr.length){
                tmpFront = 0;
            }
            if(capacity == index+1){
                break;
            }
        }
        //Convert new array as queue
        this.arr = newArr;
        System.out.println("New array capacity: "+this.arr.length);
        //Reset front and rear values
        this.front = 0;
        this.rear = index;
    }
}
