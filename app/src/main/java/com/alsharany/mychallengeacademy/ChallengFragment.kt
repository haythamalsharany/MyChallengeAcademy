package com.alsharany.mychallengeacademy

import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ChallengFragment : Fragment(), InputDialogFragmen.Callbacks {

    private lateinit var studentRecyclerView: RecyclerView
    private val studentViewModel by lazy {
        ViewModelProviders.of(this).get(StudentViewModel::class.java)
    }
    private var adapter: StudentAdapter? = null
    private lateinit var noDataTextView: TextView
    private lateinit var addCrimeButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_challeng, container, false)
        studentRecyclerView = view.findViewById(R.id.Student_recycler_view) as RecyclerView
        noDataTextView = view.findViewById(R.id.empty_list_textview) as TextView
        addCrimeButton = view.findViewById(R.id.addCrimeBtn) as Button
        studentRecyclerView.layoutManager = LinearLayoutManager(context)
        updateUI()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addCrimeButton.setOnClickListener {
            InputDialogFragmen.newInstance().apply {
                setTargetFragment(this@ChallengFragment, 0)
                show(this@ChallengFragment.requireFragmentManager(), "input")

            }
        }
    }

    private inner class StudentHolder(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener {
        val st_number: TextView = itemView.findViewById(R.id.Student_number_tv)
        val st_name: TextView = itemView.findViewById(R.id.Student_name_tv)
        val st_passed: TextView = itemView.findViewById(R.id.Student_isPassed_tv)
        val deletImageButton = itemView.findViewById(R.id.delate_btn) as ImageButton
        private lateinit var student: Student

        init {
            deletImageButton.setOnClickListener(this)
        }

        fun bind(item: Student) {

            this.student = item
            st_number.text = this.student.number.toString()
            st_name.text = this.student.name
            if (this.student.passed)
                st_passed.text = "passed "
            else
                st_passed.text = "faild "


        }

        override fun onClick(v: View?) {
            onStudentDelete(adapterPosition)


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

            return students.size
        }


    }

    private fun updateUI() {

        val students =
            studentViewModel.Students
        adapter = StudentAdapter(students)
        studentRecyclerView.adapter = adapter
        if (students.isNotEmpty()) {
            noDataTextView.visibility = View.GONE
            addCrimeButton.visibility = View.GONE
        } else {

            noDataTextView.visibility = View.VISIBLE
            addCrimeButton.visibility = View.VISIBLE
        }


    }

    companion object {

        @JvmStatic
        fun newInstance() =
            ChallengFragment()

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.student_menue, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add_student -> {
                InputDialogFragmen.newInstance().apply {
                    setTargetFragment(this@ChallengFragment, 0)
                    show(this@ChallengFragment.requireFragmentManager(), "input")
                }
                true
            }
            else ->
                return super.onOptionsItemSelected(item)


        }


    }

    override fun onStudentAdd(student: Student) {
        studentViewModel.addStudent(student)
        updateUI()
    }

    override fun onStudentDelete(position: Int) {
        studentViewModel.deleteStudent(position)
        updateUI()

    }
}
