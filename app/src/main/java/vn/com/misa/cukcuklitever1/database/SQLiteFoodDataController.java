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

import vn.com.misa.cukcuklitever1.menu_cook.entity.Food;

/**
 * Lấy danh sách các thực đơn trong database
 */
public class SQLiteFoodDataController extends SQLiteOpenHelper {
    private String DB_PATH = "//data//data//%s//databases//";
    private static String DB_NAME = "Food.sqlite";
    private SQLiteDatabase database;
    private final Context mContext;
    private final String NAME_TABLE = "food";

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
        int id, price;
        boolean status;
        Food food;
        try {
            openDatabase();
            Cursor cs;
            String sql = "SELECT * FROM " + NAME_TABLE;
            cs = database.rawQuery(sql, null);
            while (cs.moveToNext()) {
                id = cs.getInt(0);
                name = cs.getString(1);
                price = cs.getInt(2);
                unit = cs.getString(3);
                color = cs.getString(4);
                icon = cs.getString(5);
                int statusFromSqlite = cs.getInt(6);
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
     * Thêm 1 thực đơn vào danh sách
     *
     * @param food thực đơn
     * @return kết quả thêm là thành công hay lỗi
     */
    public boolean insertFood(Food food) {
        boolean result = false;
        try {
            openDatabase();
            ContentValues cv = new ContentValues();
            cv.put("name", food.getName());
            cv.put("price", food.getPrice());
            cv.put("unit", food.getUnit());
            cv.put("color", food.getColor());
            cv.put("icon", food.getIcon());
            cv.put("status", food.isStatus() ? 1 : 0);
            if (database.insert(NAME_TABLE, null, cv) > -1)
                result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return result;
    }

    /**
     * Sửa thực đơn
     *
     * @param food món đã sửa
     * @param id   id món muốn sửa
     * @return kết quả
     */
    public boolean editFood(Food food, int id) {
        boolean result = false;
        try {
            openDatabase();
            ContentValues cv = new ContentValues();
            cv.put("name", food.getName());
            cv.put("price", food.getPrice());
            cv.put("unit", food.getUnit());
            cv.put("color", food.getColor());
            cv.put("icon", food.getIcon());
            cv.put("status", food.isStatus() ? 1 : 0);
            if (database.update(NAME_TABLE, cv, "id ='" + id + "'", null) > 0) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return result;
    }

    public boolean removeFood(int id) {
        boolean result = false;
        try {
            openDatabase();
            int c = database.delete(NAME_TABLE, "id ='" + id + "'", null);
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
     *
     * @throws IOException báo ngoại lệ khi copy không thành công
     */
    private void copyDataBase() throws IOException {
        InputStream mInput = mContext.getAssets().open(DB_NAME);
        OutputStream mOutput = new FileOutputStream(DB_PATH + DB_NAME);
        byte[] buffer = new byte[1024];
        int lenght;
        while ((lenght = mInput.read(buffer)) > 0) {
            mOutput.write(buffer, 0, lenght);
        }
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

    /**
     * Mở database
     * create by lvhung on 5/25/2019
     *
     * @throws SQLException ngoại lệ khi mở thất bại
     */
    private void openDatabase() throws SQLException {
        //nếu chưa database thì copy từ assets
        if (!checkExistDatabase()) {
            this.getReadableDatabase();
        }
        try {
            copyDataBase();
            database = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.OPEN_READWRITE);
        } catch (Exception e) {
            throw new Error("Error coppying database");
        }
    }

    @Override
    public synchronized void close() {
        if (database != null)
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
