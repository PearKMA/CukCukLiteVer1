package vn.com.misa.cukcuklitever1.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import vn.com.misa.cukcuklitever1.edit_unit.entity.Unit;
import vn.com.misa.cukcuklitever1.menu_cook.entity.Food;

/**
 * Lấy danh sách các thực đơn trong database
 * Edited by lvhung at 5/30/2019
 */
public class SQLiteFoodDataController extends SQLiteOpenHelper {
    private String DB_PATH = "//data//data//%s//databases//";
    private static String DB_NAME = "Food.sqlite";
    private SQLiteDatabase database;
    private final Context mContext;
    private final String FOOD_TABLE = "food";
    private final String UNIT_TABLE = "unit";

    /**
     * Constructor
     * create by lvhung on 5/25/2019
     */
    public SQLiteFoodDataController(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
        assert context != null;
        DB_PATH = String.format(DB_PATH, context.getPackageName());
        this.mContext = context;
    }

    /**
     * lấy tất cả danh sách thực đơn trong db
     * create by lvhung on 5/25/2019
     *
     * @return list food
     */
    public ArrayList<Food> getAllFood() {
        ArrayList<Food> foods = new ArrayList<>();
        String name, unit, color, icon;
        int id;
        double price;
        boolean status;
        Food food;
        try {
            openDatabase();
            Cursor cs;
            String sql = "SELECT * FROM " + FOOD_TABLE;
            cs = database.rawQuery(sql, null);
            while (cs.moveToNext()) {
                id = cs.getInt(0);
                name = cs.getString(1);
                price = cs.getDouble(2);
                int idUnit = cs.getInt(3);  //id unit trong table unit
                unit = getNameUnitById(idUnit);
                color = cs.getString(4);
                icon = cs.getString(5);
                int statusFromSqlite = cs.getInt(6);    //trạng thái của món trong sqlite; 0: đang mở, 1: ngừng bán
                status = statusFromSqlite != 0;
                food = new Food(id, name, price, unit, color, icon, status);
                foods.add(food);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return foods;
    }

    /**
     * Lấy tất cả danh sách đơn vị tính
     * create by lvhung on 5/31/2019
     *
     * @return danh sách đơn vị tính
     */
    public ArrayList<Unit> getAllUnit() {
        ArrayList<Unit> units = new ArrayList<>();
        String unitName;
        int id;
        Unit unit;
        try {
            openDatabase();
            Cursor cs;
            String sql = "SELECT * FROM " + UNIT_TABLE;
            cs = database.rawQuery(sql, null);
            while (cs.moveToNext()) {
                id = cs.getInt(0);
                unitName = cs.getString(1);
                unit = new Unit(id, unitName);
                units.add(unit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return units;
    }

    /**
     * Lấy tên đơn vị tính theo id (chỉ dùng trong hàm lấy tất cả danh sách món)
     * create by lvhung on 5/31/2019
     *
     * @param id id của đơn vị tính
     * @return tên đơn vị tính
     */
    private String getNameUnitById(int id) {
        String name = "";
        try {
            String selectQuery = "SELECT * FROM " + UNIT_TABLE + " WHERE id = " + id;
            Cursor cursor = database.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                name = cursor.getString(1);
            }
            cursor.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }

    /**
     * Thêm mới 1 đơn vị tính
     * create by lvhung on 5/31/2019
     *
     * @param unit đối tượng đơn vị tính
     * @return kết quả
     */
    public boolean insertUnit(Unit unit) {
        boolean result = false;
        try {
            openDatabase();
            ContentValues cv = new ContentValues();
            cv.put("unit", unit.getUnit());
            if (database.insert(UNIT_TABLE, null, cv) > -1)
                result = true;
        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
        } finally {
            close();
        }
        return result;
    }

    /**
     * Thêm 1 thực đơn vào danh sách
     * Edited by lvhung at 5/30/2019
     *
     * @param food thực đơn
     * @return kết quả thêm là thành công hay lỗi
     */
    public boolean insertFood(Food food) {
        boolean result = false;
        try {
            ContentValues cv = new ContentValues();
            cv.put("name", food.getName());
            cv.put("price", food.getPrice());
            //tìm id unit theo tên
            ArrayList<Unit> units = getAllUnit();
            if (units != null && units.size() > 0) {
                for (Unit item : units) {
                    if (item.getUnit().equalsIgnoreCase(food.getUnit())) {
                        cv.put("unit", item.getId());
                        break;
                    }
                }
            }
            cv.put("color", food.getColor());
            cv.put("icon", food.getIcon());
            cv.put("status", food.isStatus() ? 1 : 0);
            openDatabase();
            if (database.insert(FOOD_TABLE, null, cv) > -1)
                result = true;
        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
        } finally {
            close();
        }
        return result;
    }

    /**
     * Sửa thực đơn
     * Edited by lvhung at 5/30/2019
     *
     * @param food món đã sửa
     * @param id   id món muốn sửa
     * @return kết quả
     */
    public boolean editFood(Food food, int id) {
        boolean result = false;
        try {
            ContentValues cv = new ContentValues();
            cv.put("name", food.getName());
            cv.put("price", food.getPrice());
            //tìm id unit theo tên
            ArrayList<Unit> units = getAllUnit();
            if (units != null && units.size() > 0) {
                for (Unit item : units) {
                    if (item.getUnit().equalsIgnoreCase(food.getUnit())) {
                        cv.put("unit", item.getId());
                        break;
                    }
                }
            }
            cv.put("color", food.getColor());
            cv.put("icon", food.getIcon());
            cv.put("status", food.isStatus() ? 1 : 0);
            openDatabase();
            if (database.update(FOOD_TABLE, cv, "id = " + id, null) > 0) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return result;
    }

    /**
     * Chỉnh sửa đươn vị tính
     * create by lvhung on 5/31/2019
     *
     * @param unit đơn vị tính
     * @param id   id đơn vị tính
     * @return kết quả
     */
    public boolean editUnit(Unit unit, int id) {
        boolean result = false;
        try {
            openDatabase();
            ContentValues cv = new ContentValues();
            cv.put("unit", unit.getUnit());
            if (database.update(UNIT_TABLE, cv, "id = " + id, null) > 0) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return result;
    }

    /**
     * Xóa món ăn theo id
     * Edited by lvhung at 5/31/2019
     *
     * @param id id món
     * @return kết quả
     */
    public boolean removeFood(int id) {
        boolean result = false;
        try {
            openDatabase();
            int c = database.delete(FOOD_TABLE, "id = " + id, null);
            if (c > 0) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return result;
    }

    /**
     * Xóa đơn vị tính theo id
     *
     * @param id id đơn vị tính
     * @return kết quả
     */
    public boolean removeUnit(int id) {
        boolean result = false;
        try {
            openDatabase();
            int c = database.delete(UNIT_TABLE, "id = " + id, null);
            if (c > 0) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return result;
    }

    /**
     * Kiểm tra database đã có trong thiết bị hay chưa
     * create by lvhung on 5/25/2019
     * Edited by lvhung at 5/30/2019
     *
     * @return boolean
     */
    private boolean checkExistDatabase() {
        try {
            String myPath = DB_PATH + DB_NAME;
            File fileDB = new File(myPath);
            return fileDB.exists();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Copy database từ assets vào thiết bị
     * create by lvhung on 5/25/2019
     * Edited by lvhung at 5/30/2019
     *
     * @throws IOException báo ngoại lệ khi copy không thành công
     */
    private void copyDataBase() throws IOException {
        InputStream mInput = null;
        OutputStream mOutput = null;
        try {
            mInput = mContext.getAssets().open(DB_NAME);
            mOutput = new FileOutputStream(DB_PATH + DB_NAME);
            byte[] buffer = new byte[1024];
            int lenght;
            while ((lenght = mInput.read(buffer)) > 0) {
                mOutput.write(buffer, 0, lenght);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (mOutput != null) {
                mOutput.flush();
            }
            if (mOutput != null) {
                mOutput.close();
            }
            if (mInput != null) {
                mInput.close();
            }
        }
    }

    /**
     * Mở database
     * create by lvhung on 5/25/2019
     * Edited by lvhung at 5/30/2019
     *
     * @throws SQLException ngoại lệ khi mở thất bại
     */
    private void openDatabase() throws SQLException {
        //nếu chưa database thì copy từ assets
        if (!checkExistDatabase()) {
            try {
                copyDataBase();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            this.getReadableDatabase();
            database = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.OPEN_READWRITE);
        } catch (Exception e) {
            throw new Error("Error coppying database");
        }
    }

    /**
     * đóng database
     * Edited by lvhung at 5/30/2019
     */
    @Override
    public synchronized void close() {
        if (database != null&&database.isOpen())
            database.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
