package com.alsharany.mychallengeacademy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ChallengFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var studentRecyclerView: RecyclerView
    private val studentViewModel by lazy {
        ViewModelProviders.of(this).get(StudentViewModel::class.java)
    }
    private var adapter: StudentAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_challeng, container, false)
        studentRecyclerView = view.findViewById(R.id.Student_recycler_view) as RecyclerView
        studentRecyclerView.layoutManager = LinearLayoutManager(context)
        updateUI()

        return view
    }

    private inner class StudentHolder(view: View) : RecyclerView.ViewHolder(view) {
        val st_number: TextView = itemView.findViewById(R.id.Student_number_tv)
        val st_name: TextView = itemView.findViewById(R.id.Student_name_tv)
        val st_passed: TextView = itemView.findViewById(R.id.Student_isPassed_tv)
        private lateinit var student: Student
        fun bind(item: Student) {

            this.student = item
            st_number.text = this.student.number.toString()
            st_name.text = this.student.name
            if (this.student.passed)
                st_passed.text = "passed "
            else
                st_passed.text = "faild "

        }
    }

    private inner class StudentAdapter(var students: List<Student>) :
        RecyclerView.Adapter<StudentHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentHolder {
            val view = layoutInflater.inflate(R.layout.item_list, parent, false)
            return StudentHolder(view)
        }

        override fun onBindViewHolder(holder: StudentHolder, position: Int) {
            val student = students[position]
            holder.bind(student)

        }

        override fun getItemCount(): Int {
            return studentViewModel.Students.size
        }


    }

    private fun updateUI() {
        val students =
            studentViewModel.Students
        adapter = StudentAdapter(students)
        studentRecyclerView.adapter = adapter
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            ChallengFragment()

    }
}