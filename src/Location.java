import java.util.Objects;

public class Location {

    /** X coordinate of this location. **/
    public int xCoord;

    /** Y coordinate of this location. **/
    public int yCoord;


    /** Creates a new location with the specified integer coordinates. **/
    public Location(int x, int y)
    {
        xCoord = x;
        yCoord = y;
    }

    /** Creates a new location with coordinates (0, 0). **/
    public Location()
    {
        this(0, 0);
    }

    /** Метод для сравнение объектов **/
    @Override
    public boolean equals(Object o) {
        // Сравниваем объект с объектом Location если они не одинаковые вернёт false
        if (!(o instanceof Location)) return false;
        // Приведём  объект в  объект Location
        Location location = (Location) o;
        // Сравниваем результаты если равны true иначе false
        return xCoord == location.xCoord && yCoord == location.yCoord;
    }


    /** Метод получения хэша кода на основе объекта **/
    @Override
    public int hashCode() {
        //Преобразуем объект в хэш код
        return Objects.hash(xCoord, yCoord);
    }
}
