package com.example.myrecycleview

import android.os.Bundle
//import android.support.constraint.ConstraintLayout

import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager

import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom_sheet.*
//import kotlinx.android.synthetic.main.bottom_sheet.button
import kotlinx.android.synthetic.main.main_content.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var linearLayoutManager: LinearLayoutManager

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>//для футбара

    private val persons = arrayListOf<Person>()
    private fun initializeData() {

        persons.add(Person("Хаски из Аляски","Ему 5 лет,а тебе?))",R.drawable.husky))
        persons.add(Person("Помираниан (как помираниум, только шпиц)","Ему 6 лет,а тебе?))",R.drawable.pomeranian))
        persons.add(Person("Шипдог (как шиппер,только собака; или как корабль ","Ему 7 лет,а тебе?))",R.drawable.sheepdog))
        persons.add(Person("Это спрингер как машина,вжух,вжух","Ему 5 лет,а тебе?))",R.drawable.springer))
        persons.add(Person("Хаски из Аляски","Ему 5 лет",R.drawable.husky))
        persons.add(Person("Помираниан ","Ему 6 лет,а тебе?))",R.drawable.pomeranian))
        persons.add(Person("Шипдог","Ему 7 лет",R.drawable.sheepdog))
        persons.add(Person("Это спрингер как машина, вжух,вжух","Ему 5 лет",R.drawable.springer))
        persons.add(Person("Кекерики","Ему 5 лет",R.drawable.husky))
        persons.add(Person("Бадум-тсс ","Ему 6 лет)",R.drawable.pomeranian))
        persons.add(Person("Шип-шип","Ему 7 лет",R.drawable.sheepdog))
        persons.add(Person("Вжух,вжух","Ему 5 лет",R.drawable.springer))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeData()
        linearLayoutManager = LinearLayoutManager(this)
        rv.apply {
            setHasFixedSize(true)//размер RecyclerView не будет изменяться
            layoutManager = linearLayoutManager
            adapter = MyRecyclerAdapter(persons)
        }
        //вызов функции,которая собирает футор - в разработке
        initView()
    }

    private fun initView() {
        //Устанавливаем листенер (обработчик событий) на кнопку
        buttonMain.setOnClickListener(this)

        //подключаем дейстия на BottomSheet
        bottomSheetBehavior = BottomSheetBehavior.from<LinearLayout>(bottom_sheet)

        //управление поведением BottomSheet
        bottomSheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                // Реакция на события перетаскивания (свайп BottomSheet)
            }

            // Реакция на изменение состояния
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        buttonMain.text = "Slide Up"
                    }
                    BottomSheetBehavior.STATE_HIDDEN -> {

                    }
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        buttonMain.text = "Slide Down"
                    }
                    BottomSheetBehavior.STATE_DRAGGING -> {

                    }
                    BottomSheetBehavior.STATE_SETTLING -> {

                    }
                }
            }
        })
    }

    //Листенер для того, на что можно кликать
    override fun onClick(clickView: View?) {
        when (clickView) {//Если нажали на какую-то кнопку
            buttonMain -> {//нажали на кнопку с id button
                slideUpDownBottomSheet()
            }
        }
    }

    private fun slideUpDownBottomSheet() {
        //Проверяем состояние BottomSheet
        if (bottomSheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
            //Если BottomSheet не открыт, открываем его
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            buttonMain.text = "Slide Down"
        } else {
            //Сворачиваем BottomSheet
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            buttonMain.text = "Slide Up"
        }
    }


}
