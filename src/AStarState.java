import java.util.HashMap;
import java.util.Map;
import java.util.*;

public class AStarState {

    /** This is a reference to the map that the A* algorithm is navigating. **/
    private Map2D map;


    /** Нестатическое поле для открытых вершин **/
    private Map<Location, Waypoint> open_waypoints = new HashMap<Location, Waypoint>();

    /** Нестатическое поле для закрытых вершин **/
    private Map<Location, Waypoint> closed_waypoints = new HashMap<Location, Waypoint> ();

    /**
     * Initialize a new state object for the A* pathfinding algorithm to use.
     **/
    public AStarState(Map2D map)
    {
        if (map == null)
            throw new NullPointerException("map cannot be null");

        this.map = map;
    }

    /** Returns the map that the A* pathfinder is navigating. **/
    public Map2D getMap()
    {
        return map;
    }

    /**
     * Этот метод проверяет все вершины в наборе открытых вершин
     * после этого она должна вернуть ссылку на вершину с наименьшей общей стоимостью
     * Если в открытом наборе нет вершин, функция вернёт NULL
     **/
    public Waypoint getMinOpenWaypoint()
    {
        //Если ли открытые вершины, если нету вернёт NULL
        if (numOpenWaypoints() == 0)
            return null;

        // Создаём набор ключей всех открытых вершин
        // Перебираем данный наборы для того чтобы хранить наилучшую вершину
        // и стоимость этой вершины.

        // возвращает набор всех ключей отображения
        Set open_waypoint_keys = open_waypoints.keySet();
        Iterator i = open_waypoint_keys.iterator();
        Waypoint best = null;
        float best_cost = Float.MAX_VALUE;

        // Проверяем все открытые вершины
        while (i.hasNext())
        {
            // Получаем текущее расположение
            Location location = (Location)i.next();
            // Получаем какую то открытую вершину
            Waypoint waypoint = open_waypoints.get(location);
            // Получаем цену которую мы получим при переход на открытую вершину.
            float waypoint_total_cost = waypoint.getTotalCost();

            // Если общая стоимость текущей вершины меньше
            // чем стоимость лучшей вершины то мы меняем его
            if (waypoint_total_cost < best_cost)
            {
                best = open_waypoints.get(location);
                best_cost = waypoint_total_cost;
            }

        }
        // Вернёт вершину с наименьшей стоимостью
        return best;
    }

    /**
        * Метод в котором добавляются открытые вершины
     **/
    public boolean addOpenWaypoint(Waypoint newWP)
    {
        //Находим местоположение вершины
        Location findlocation = newWP.getLocation();
        //Проверяем есть ли уже открытая точка на данном расположений
        if (open_waypoints.containsKey(findlocation))
        {
            //Если данная точка уже открытая
            Waypoint current_waypoint = open_waypoints.get(findlocation);
            //Проверяем есть у данной вершины цена посещения меньше чем у предыдущего
            if (newWP.getPreviousCost() < current_waypoint.getPreviousCost())
            {
                //Заменяю старую вершину и возвращаю true
                open_waypoints.put(findlocation,newWP);
                return true;
            }
            return false;
        }
        //Заменяю старую вершину и возвращаю true
        open_waypoints.put(findlocation,newWP);
        return true;
    }


    /** Этот метод возвращает количество точек в наборе открытых вершин. **/
    public int numOpenWaypoints()
    {
        return open_waypoints.size();
    }


    /**
     * Этот метод превращает открытую вершину в закрытую вершину
     **/
    public void closeWaypoint(Location loc)
    {
        // Удаляет открытую вершину
        Waypoint waypoint = open_waypoints.remove(loc);
        // Закидываю данную вершину в закрытую
        closed_waypoints.put(loc, waypoint);
    }

    /**
     * Возвращает true если указанное местоположение встречается в наборе закрытых вершин
     **/
    public boolean isLocationClosed(Location loc)
    {
        return closed_waypoints.containsKey(loc);
    }


}
