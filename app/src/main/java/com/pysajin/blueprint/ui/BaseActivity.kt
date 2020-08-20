package com.pysajin.blueprint.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.pysajin.blueprint.model.ItemList

abstract class BaseActivity<B : ViewDataBinding>(private val layoutId: Int) : AppCompatActivity() {
    enum class ActivityPos(val pos:Int){MAIN(0), MAP(1), MAPREGIST(2)}

    protected lateinit var binding: B
    private var REQUEST_REGIST = 1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this
    }

    fun changeActivity(count: Int, data : ItemList? = null) {
        val intent: Intent
        when (count) {
            ActivityPos.MAIN.pos -> {
                intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

            ActivityPos.MAP.pos -> {
                intent = Intent(this, MapActivity::class.java)
                //intent에 데이터 넣어서 넘기면 됨
                intent.putExtra("datas", data)
                startActivity(intent)
                finish()
            }
            ActivityPos.MAPREGIST.pos->{
                intent = Intent(this, MapRegistActivity::class.java)
                startActivityForResult(intent, REQUEST_REGIST)
                finish()
            }
        }
    }
}