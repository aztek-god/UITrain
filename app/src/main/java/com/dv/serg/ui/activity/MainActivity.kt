package com.dv.serg.ui.activity

import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.GridLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import com.dv.serg.ui.R
import com.dv.serg.ui.adapter.SimpleAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var isToolbarVisible: Boolean = true

    val imageList: List<String> = listOf(
            "https://goo.gl/s8XwVb",
            "https://goo.gl/s8XwVb",
            "https://goo.gl/s8XwVb",
            "https://goo.gl/s8XwVb",
            "https://goo.gl/s8XwVb",
            "https://goo.gl/s8XwVb",
            "https://goo.gl/s8XwVb",
            "https://goo.gl/s8XwVb",
            "https://goo.gl/s8XwVb",
            "https://goo.gl/s8XwVb",
            "https://goo.gl/s8XwVb",
            "https://goo.gl/s8XwVb",
            "https://goo.gl/s8XwVb",
            "https://goo.gl/s8XwVb",
            "https://goo.gl/s8XwVb"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.apply {
            adapter = SimpleAdapter(imageList)
            layoutManager = object : GridLayoutManager(this@MainActivity, 2, GridLayout.VERTICAL, false) {
                override fun smoothScrollToPosition(recyclerView: RecyclerView?, state: RecyclerView.State?, position: Int) {
                    super.smoothScrollToPosition(recyclerView, state, position)
                    val scroller = object : LinearSmoothScroller(this@MainActivity) {
                        override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics?): Float {
                            return 2.toFloat() / displayMetrics!!.densityDpi
                        }
                    }

                    scroller.targetPosition = position
                    startSmoothScroll(scroller)
                }
            }

            adapter?.notifyDataSetChanged()

            addOnScrollListener(
                    object : RecyclerView.OnScrollListener() {
                        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                            super.onScrolled(recyclerView, dx, dy)

                            val moveY = if (dy <= 0) {
                                isToolbarVisible = true
                                0
                            } else {
                                isToolbarVisible = false
                                -toolbarContainer.height * 2
                            }

                            toolbarContainer
                                    .animate()
                                    .translationY(moveY.toFloat())
                                    .setStartDelay(50)
                                    .setDuration(300)
                                    .start()
                        }
                    }
            )
        }

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


//        toolbar.apply {
//            setSupportActionBar(this)
//            supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        }

//        button.apply {
//            setOnClickListener {
//
//                val moveY = if (isToolbarVisible) -toolbarContainer.height * 2 else 0
//
//                toolbarContainer
//                        .animate()
//                        .translationY(moveY.toFloat())
//                        .setStartDelay(100)
//                        .setDuration(300)
//                        .start()
//
//                isToolbarVisible = !isToolbarVisible
//            }
//        }
    }
}
