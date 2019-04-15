
package com.example.peter.myapplication.ui.main


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.peter.myapplication.R
import com.example.peter.myapplication.data.Item
import kotlinx.android.synthetic.main.edit_fragment.*


class EditFragment : Fragment() {

    companion object {
        fun newInstance() = EditFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View =  inflater.inflate(R.layout.edit_fragment, container, false)
        activity?.actionBar?.setHomeAsUpIndicator(R.drawable.ic_close_black_24dp)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

   val values = arrayOf("LOW","MEDIUM","HIGH")

        val typesAr= arrayOf("Bags","Sachets","Cups","Bottles","Pieces", "Cartons", "Mudu")

        itemQuantity.minValue=1
        itemQuantity.maxValue=100
        itemQuantity.value=1

        itemPriority.minValue=1
        itemPriority.maxValue=3
        itemPriority.displayedValues= values
        itemPriority.value=2

        itemQuantityType.minValue=1
        itemQuantityType.maxValue=7
        itemQuantityType.displayedValues=typesAr
        itemQuantityType.value=2

        activity?.title="Add Item"
        setHasOptionsMenu(true)

    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_edit, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.save-> {
                if (saveNote()){closePage()}


                return true
            }
            R.id.close->{
               closePage()
                return true
            }
            android.R.id.home->{
                closePage()
                return true
            }


        }
        return super.onOptionsItemSelected(item)
    }
    private fun closePage(){
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.container, MainFragment.newInstance())
            ?.commitNow()
    }

    private fun saveNote():Boolean{

        lateinit var viewModel: MainViewModel

        val name = itemName.text.toString()
        val price = itemPrice.text.toString()
        val quantity= itemQuantity.value
        val quantype= itemQuantityType.value
        val prior=itemPriority.value

        if (name.isBlank()){
            itemName.error="Item Name Cannot be Empty"
            return false
        }
        if (price.isBlank()){
            itemPrice.error="Item Price Cannot be Empty"
            return false

        }        else{
        val newItem = Item(0, name, price, prior, quantype, quantity)


        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.insert(newItem)
        return true}


}
}