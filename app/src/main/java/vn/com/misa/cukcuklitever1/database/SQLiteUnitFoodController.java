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

import vn.com.misa.cukcuklitever1.add_food.entity.Unit;

/**
 * Lấy danh sách đơn vị tính từ database
 * create by lvhung on 5/28/2019
 */
public class SQLiteUnitFoodController extends SQLiteOpenHelper {
    public String DB_PATH = "//data//data//%s//databases//";
    private static String DB_NAME = "Food.sqlite";
    private SQLiteDatabase database;
    private final Context mContext;
    private final String NAME_TABLE = "units";

    /**
     * Constructor
     * create by lvhung on 5/28/2019
     *
     * @param context activity
     */
    public SQLiteUnitFoodController(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
        assert context != null;
        DB_PATH = String.format(DB_PATH, context.getPackageName());
        this.mContext = context;
    }

    /**
     * Lấy tất cả danh sách đơn vị tính
     * create by lvhung on 5/28/2019
     *
     * @return danh sách
     */
    public ArrayList<Unit> getAllUnit() {
        ArrayList<Unit> units = new ArrayList<>();
        String unit;
        int id;
        try {
            openDatabase();
            Cursor cs;
            String sql = "SELECT * FROM " + NAME_TABLE;
            cs = database.rawQuery(sql, null);
            while (cs.moveToNext()) {
                id = cs.getInt(0);
                unit = cs.getString(1);
                units.add(new Unit(id, unit));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return units;
    }

    /**
     * Thêm một đơn vị tính
     * create by lvhung on 5/28/2019
     *
     * @param unit đơn vị tính
     * @return kết quả
     */
    public boolean insertUnit(String unit) {
        boolean result = false;
        try {
            openDatabase();
            ContentValues cv = new ContentValues();
            cv.put("unit", unit);
            if (database.insert(NAME_TABLE, null, cv) > -1) {
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
     * Sửa đơn vị tính
     * create by lvhung on 5/28/2019
     *
     * @param unit đơn vị tính
     * @param id   id của đơn vị
     * @return kết quả
     */
    public boolean editUnit(String unit, int id) {
        boolean result = false;
        try {
            openDatabase();
            ContentValues cv = new ContentValues();
            cv.put("unit", unit);
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

    /**
     * Xóa đơn vị tính
     * create by lvhung on 5/28/2019
     *
     * @param id id đơn vị tính
     * @return kết quả
     */
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
     * Mở database, nếu chưa có thì copy từ mục assets
     * create by lvhung on 5/28/2019
     *
     * @throws SQLException
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

    /**
     * Kiểm tra đã tồn tại database hay chưa
     * create by lvhung on 5/28/2019
     *
     * @return kết quả
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
     * create by lvhung on 5/28/2019
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
     * Đóng database
     */
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
