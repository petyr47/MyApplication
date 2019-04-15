package com.example.peter.myapplication.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.peter.myapplication.R
import com.example.peter.myapplication.data.Item
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)

        return inflater.inflate(R.layout.main_fragment, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val adapter= ItemAdapter()

        activity?.title="Shopping List"

        recyclerView.layoutManager=LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter=adapter

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.allItems.observe(viewLifecycleOwner, Observer<List<Item>>(){
            adapter.setItems(it)

        })

        fab.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, EditFragment.newInstance())
                ?.commitNow()
        }
        val simpleItemTouchCallback= object : ItemTouchHelper.SimpleCallback(0,
            ItemTouchHelper.LEFT or  ItemTouchHelper.RIGHT){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
              viewModel.delete(adapter.getItemAt(viewHolder.adapterPosition))

            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
               return false
            }


        }

        val itemTouchHelper=ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)

    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_main, menu)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.del-> {
                viewModel.deleteAllItems()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


}
