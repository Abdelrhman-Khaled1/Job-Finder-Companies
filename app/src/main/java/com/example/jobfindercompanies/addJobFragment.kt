package com.example.jobfindercompanies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.Calendar

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [addJobFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class addJobFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null



    private lateinit var JobTitle : EditText
    private lateinit var JobDescription : EditText
    private lateinit var JobRequirement : EditText
    private lateinit var JobLocation : EditText
    private lateinit var checkBox: CheckBox
    private lateinit var button : Button

    var mAuth : FirebaseAuth? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_job, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment addJobFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            addJobFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Jobs")

        mAuth = FirebaseAuth.getInstance()


        JobTitle = view.findViewById(R.id.JobTitle)
        JobDescription = view.findViewById(R.id.JobDescription)
        JobRequirement = view.findViewById(R.id.JobRequirement)
        JobLocation = view.findViewById(R.id.JobLocation)
        checkBox = view.findViewById(R.id.JobDisable)
        button = view.findViewById(R.id.publish)


        button.setOnClickListener(View.OnClickListener {

            val title = JobTitle.text.toString()
            val description = JobTitle.text.toString()
            val requirement = JobTitle.text.toString()
            val location = JobTitle.text.toString()
            val disable = checkBox.isChecked

            if(title.isNotEmpty() && description.isNotEmpty() && requirement.isNotEmpty() && location.isNotEmpty()){
                val job : Job = Job()

                job.title = title
                job.description = description
                job.requirement = requirement
                job.location = location
                job.forDisabledPeople = disable

                job.date = getCurrentDate()

//                job.publisher = mAuth?.currentUser.toString()


                val id = myRef.push().key
                job.id = id.toString()
                myRef.child(id.toString()).setValue(job).addOnCompleteListener {
                    if(it.isSuccessful){
                        Toast.makeText(activity,"Job Published",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })


    }

    private fun getCurrentDate() : String{
        val calendar = Calendar.getInstance()
        val mdformat = SimpleDateFormat("EEEE hh:mm a")
        val strDate = mdformat.format(calendar.time)
        return strDate
    }
}