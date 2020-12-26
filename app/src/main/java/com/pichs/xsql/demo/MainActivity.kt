package com.pichs.xsql.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.pichs.xsql.XSql
import com.pichs.xsql.dao.IBaseDao
import com.pichs.xsql.property.XSqlProperties
import com.pichs.xsql.utils.XSqlLog
import com.pichs.xsql.where.Where
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var userDao: IBaseDao<UserInfo>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "数据库Demo"
        userDao = XSql.getDBManager(this).getBaseDao(UserInfo::class.java)

        insert.setOnClickListener {
            Toast.makeText(this, "插入", Toast.LENGTH_SHORT).show()
            val userinfo = UserInfo()

            userinfo.age = et_age.text.toString().toInt()
            userinfo.name = et_name.text.toString()
            val result = userDao.insert(userinfo)
            XSqlLog.d("MainActivity  insert result: $result")
            queryAll()
        }

        update.setOnClickListener {
            Toast.makeText(this, "更新", Toast.LENGTH_SHORT).show()
            // todo 更新有问题，没有参照物， 唯一id，或者唯一键， 或者条件。 重新制定规则。
        }


        delete.setOnClickListener {
            Toast.makeText(this, "删除", Toast.LENGTH_SHORT).show()
            val delete = userDao.delete(UserInfo("卡卡西", null))
            XSqlLog.d("MainActivity delete result: $delete")
            queryAll()
        }

        query.setOnClickListener {
            Toast.makeText(this, "查询", Toast.LENGTH_SHORT).show()
            val userinfo = UserInfo()
            userinfo.age = 10
            val list = userDao.query(userinfo)
            XSqlLog.d("MainActivity query result: $list")
            tvResult.text = list.toTypedArray().contentToString()
        }


        tquery.setOnClickListener {
            Toast.makeText(this, "T对象查询", Toast.LENGTH_SHORT).show()
            val userinfo = UserInfo()
            userinfo.age = 12
            val list = userDao.query(userinfo)
            XSqlLog.d("MainActivity tquery result: $list")
            tvResult.text = list.toTypedArray().contentToString()
        }

        whquery.setOnClickListener {
            Toast.makeText(this, "条件查询", Toast.LENGTH_SHORT).show()
            val where = Where.Builder()
//                .between("age",10,16)
//                .and()
                .startWith("name", "小")
//                .orderByDesc("age")
                .build()
            val list = userDao.query(where)
            XSqlLog.d("MainActivity whquery result: $list")
            tvResult.text = list?.toTypedArray().contentToString()
        }

        whdelete.setOnClickListener {
            Toast.makeText(this, "条件删除", Toast.LENGTH_SHORT).show()
            val res = userDao.delete(
                Where.Builder().eq("name", "卡卡西").build()
            )
            XSqlLog.d("MainActivity whdelete result: $res")
            queryAll()
        }

        whupdate.setOnClickListener {
            Toast.makeText(this, "条件更新", Toast.LENGTH_SHORT).show()
            val userinfo = UserInfo()
            userinfo.age = 29
            userDao.update(Where.Builder().eq("name", "卡卡西").build(), userinfo)
            XSqlLog.d("MainActivity whupdate result: $ res")
            queryAll()
        }

        queryAll()
    }


    fun queryAll() {
        val list = userDao.queryAll()
        tvResult.text = list.toTypedArray().contentToString()
    }
}