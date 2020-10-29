package com.alsharany.mychallengeacademy

import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import java.text.FieldPosition
import java.util.*

class InputDialogFragmen : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val v = activity?.layoutInflater?.inflate(R.layout.new_student_dialog, null)
        val onEditeText = v?.findViewById(R.id.St_no_ed) as EditText
        val nameEditeText = v?.findViewById(R.id.St_name_ed) as EditText
        val passedCheckBox = v?.findViewById(R.id.passed_checkbox) as CheckBox


        return AlertDialog.Builder(requireContext(), R.style.ThemeOverlay_AppCompat_Dialog_Alert)
            .setView(v)
            .setTitle("add new student")
            .setPositiveButton("add") { dialog, _ ->
                val s = Student(
                    UUID.randomUUID(),
                    onEditeText.text.toString().toInt(),
                    nameEditeText.text.toString(),
                    passedCheckBox.isChecked
                )
                targetFragment?.let {
                    (it as Callbacks).onStudentAdd(s)
                }

            }
            .setNegativeButton("cancel") { dialog, _ ->
                dialog.cancel()

            }.create()
    }

    interface Callbacks {
        fun onStudentAdd(student: Student)
        fun onStudentDelete(position: Int)
    }

    companion object {
        fun newInstance(): InputDialogFragmen {

            return InputDialogFragmen()
        }
    }
}