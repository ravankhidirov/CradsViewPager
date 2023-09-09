package com.example.cardsanimation


import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.cardsanimation.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var viewPager2: ViewPager2
    private lateinit var handler: Handler
    private lateinit var cardsList:ArrayList<Int>
    private lateinit var adapter: CardAdapter


    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if (binding.viewPager2.currentItem == 0)


        binding.viewPager2.registerOnPageChangeCallback(object:ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 0){
                    binding.viewPager2.setPadding(0,0,0,0)
                }else if(position == 1){
                    binding.viewPager2.setPadding(40,0,180,0)
                }
                else{
                    binding.viewPager2.setPadding(110,0,110,0)
                }
            }
        })




        init()
        setUpTransformer()
        viewPager2.registerOnPageChangeCallback(object :ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
                //handler.postDelayed(runnable,2000)
            }
        })
    }


    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }

    override fun onResume() {
        super.onResume()
        //handler.postDelayed(runnable,2000)
    }




    private val runnable = Runnable{
        viewPager2.currentItem = viewPager2.currentItem + 1
    }


    private fun setUpTransformer(){
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        transformer.addTransformer{page,position->
            val r = 1 - kotlin.math.abs(position)
            page.scaleY = 0.85f + r*0.14f
        }
        viewPager2.setPageTransformer(transformer)
    }

    private fun init(){
        viewPager2 = findViewById(R.id.viewPager2)
        handler = Handler(Looper.myLooper()!!)
        cardsList = ArrayList()
        cardsList.add(R.drawable.europe)
        cardsList.add(R.drawable.europe)
        cardsList.add(R.drawable.main)
        cardsList.add(R.drawable.travel)
        adapter = CardAdapter(cardsList,viewPager2)
        viewPager2.adapter = adapter
        viewPager2.offscreenPageLimit = 3
        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
    }
}