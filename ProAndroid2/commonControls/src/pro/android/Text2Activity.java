package pro.android;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.style.BackgroundColorSpan;
import android.text.style.StyleSpan;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

public class Text2Activity extends Activity 
{
	@Override 
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.texts2);

        TextView  tv1 = (TextView)findViewById(R.id.cctv1ValueText);
        tv1.setText("���� TextView��� ��");
        EditText et1 =(EditText)this.findViewById(R.id.ccet1ValueText);
        et1.setText("���� EditText��� ��");

        EditText et2 =(EditText)this.findViewById(R.id.ccet2ValueText);
        et2.setText("");
        EditText et3 =(EditText)this.findViewById(R.id.ccet3ValueText);
        et3.setText("");
        EditText et4 =(EditText)this.findViewById(R.id.ccet4ValueText);
        et4.setText(R.string.styledText);

        EditText et5 =(EditText)this.findViewById(R.id.ccet5ValueText);
        et5.setText("EditText ���뿡 �������� style ����");
        Spannable spn = et5.getText();
        spn.setSpan(new BackgroundColorSpan(Color.RED), 18, 23, 
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spn.setSpan(new StyleSpan(android.graphics.Typeface.BOLD_ITALIC)
        , 18, 23, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


        TextView  tv2 = (TextView)findViewById(R.id.cctv2ValueText);
        tv2.setText("���:");
        
        AutoCompleteTextView  actv = (AutoCompleteTextView) this.findViewById(R.id.ccactv1ValueText);
        ArrayAdapter<String> aa  = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,
        new String[]  {"English", "Hebrew", "Hindi", "Spanish", "German", "Greek" });
        actv.setAdapter(aa);
        
        MultiAutoCompleteTextView mactv  = (MultiAutoCompleteTextView) this.findViewById(R.id.ccmactv1ValueText);
        ArrayAdapter<String> aa2  = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,
        new String[]  {"English", "Hebrew", "Hindi", "Spanish", "German", "Greek" });
        mactv.setAdapter(aa2);
        mactv.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    }
}
