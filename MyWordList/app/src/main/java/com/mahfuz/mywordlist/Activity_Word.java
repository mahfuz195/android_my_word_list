package com.mahfuz.mywordlist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class Activity_Word extends ActionBarActivity {

    TextView tx_word , tx_mean , tx_comment;
    Button bt_edit , bt_delete , bt_move ;
    RatingBar rb_word ;
    int prev_rating ;
    Word word ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__word);

        tx_word = (TextView)findViewById(R.id.tx_word);
        tx_mean = (TextView)findViewById(R.id.tx_meaning);
        tx_comment=(TextView)findViewById(R.id.tx_comment);

        bt_edit = (Button)findViewById(R.id.bt_edit);
        bt_delete = (Button)findViewById(R.id.bt_delete);
        bt_move = (Button)findViewById(R.id.bt_move);

        rb_word = (RatingBar)findViewById(R.id.word_ratingBar);

        word = new Word();
        word.setId(getIntent().getIntExtra("id", 0));
        word.setTag(getIntent().getStringExtra("tag"));
        word.setValue(getIntent().getStringExtra("value"));
        word.setMeaning(getIntent().getStringExtra("meaning"));
        word.setComment(getIntent().getStringExtra("comment"));


        tx_word.setText(word.getValue());
        tx_mean.setText(word.getMeaning());
        if(word.getComment().equalsIgnoreCase(""))tx_comment.setText(word.getComment());
        else tx_comment.setText(word.getComment());

        prev_rating = getIntent().getIntExtra("rate",0);
        rb_word.setRating(prev_rating);


        bt_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update_word_window(word);
            }
        });

        bt_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(Activity_Word.this);
                alertDialog.setTitle("Confirm");
                alertDialog.setMessage("Do you want to delete?");

                alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {
                        if(delete_word(word)){
                            Toast.makeText(getApplicationContext(),"Deleted",Toast.LENGTH_SHORT).show();
                            Intent it = new Intent(Activity_Word.this,MainActivity.class);
                            startActivity(it);
                            finish();
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"failed",Toast.LENGTH_SHORT).show();
                        }

                    }
                });

                alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to invoke NO event
                        dialog.cancel();
                    }
                });
                alertDialog.show();
            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity__word, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void update_word_window(final Word passed_word){
        final Word temp_word = passed_word;
        //Toast.makeText(getActivity(), "Example action.", Toast.LENGTH_SHORT).show();
        LayoutInflater inflater = LayoutInflater.from(Activity_Word.this);
        View promptView = inflater.inflate(R.layout.add_word,null);
        final AlertDialog.Builder alertDialogBuilder;
        alertDialogBuilder = new AlertDialog.Builder(Activity_Word.this);
        alertDialogBuilder.setView(promptView);
        alertDialogBuilder.setCancelable(false);

        final EditText tx_word_u = (EditText)promptView.findViewById(R.id.etx_word);
        final EditText tx_mean_u = (EditText)promptView.findViewById(R.id.etx_meaning);
        final EditText tx_comt_u = (EditText)promptView.findViewById(R.id.etx_comment);

        tx_word_u.setText(passed_word.getValue());
        tx_mean_u.setText(passed_word.getMeaning());
        tx_comt_u.setText(passed_word.getComment());

        alertDialogBuilder.setPositiveButton("Update",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String word     = tx_word_u.getText().toString().trim();
                String mean     = tx_mean_u.getText().toString().trim();
                String comment  = tx_comt_u.getText().toString().trim();

                if(word.equalsIgnoreCase("")){
                    Toast.makeText(Activity_Word.this, "no word", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(mean.equalsIgnoreCase("")){
                        Toast.makeText(Activity_Word.this,"no meaning",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        if(comment.equalsIgnoreCase(""))comment = "none.";
                        Word w = new Word();
                        w.setTag(word.substring(0,1).toUpperCase());
                        w.setMeaning(mean);
                        w.setValue(word);
                        w.setComment(comment);
                        w.setId(temp_word.getId());

                        //if success


                        if(MainActivity.dbHelper.updateWord(w)){
                            Toast.makeText(Activity_Word.this,"updated.",Toast.LENGTH_SHORT).show();
                            tx_word.setText(w.getValue());
                            tx_mean.setText(w.getMeaning());
                            tx_comment.setText(w.getComment());
                            passed_word.setComment(w.comment);
                            passed_word.setValue(w.value);
                            passed_word.setTag(w.tag);
                            passed_word.setComment(w.comment);



                            //MainActivity.word_list.clear();

                            //MainActivity.word_list = MainActivity.dbHelper.getAllWords();

                            MainActivity.PlaceholderFragment.DataSetChanged();
                        }
                        //now update the database.
                        /*
                        if(MainActivity.dbHelper.insertWord(w)){
                            Toast.makeText(Activity_Word.this,"saved.",Toast.LENGTH_SHORT).show();
                            // MainActivity.adapter.notifyDataSetChanged();
                            MainActivity.word_list.add(w);
                            MainActivity.PlaceholderFragment.DataSetChanged();
                        };
                        */

                    }
                }
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });


        alertDialogBuilder.create();
        alertDialogBuilder.show();
    }

    public boolean delete_word(Word w){
        return MainActivity.dbHelper.delete_word(w);
    }

    @Override
    public void onBackPressed() {
        //change the rating if possible ;

        int new_rating = (int)rb_word.getRating();
        if(prev_rating!=new_rating){
            word.setRate(new_rating);
            update_rating(word);
        }

        Intent it = new Intent(Activity_Word.this,MainActivity.class);
        startActivity(it);
        finish();
        super.onBackPressed();

    }

    void update_rating(Word w){
        MainActivity.dbHelper.updateWord(w);
    }
}
