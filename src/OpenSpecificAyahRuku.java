import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OpenSpecificAyahRuku extends JFrame implements ActionListener {
    private Connection connection;
    private ImageViewer imageViewer;
    //private Read rd = new Read();
    private JPanel p1, p2, p3, body, footer;
    private JLabel surahName, selection, number;
    private JComboBox surah, select, num;
    private JButton open;
    private String[] data1 = {
            "الفاتحة - al-Fātihah [THE OPENING]",
            "البقرة - al-Baqarah [THE COW]",
            "آل عمران - Āl-i-‘Imrān [THE FAMILY OF ‘IMRĀN]",
            "النساء - an-Nisā’ [WOMEN]",
            "المائدة - al-Mā’idah [THE TABLE SPREAD]",
            "الأنعام - al-An‘ām [THE CATTLE]",
            "الأعراف - al-A‘rāf [THE HEIGHTS]",
            "الأنفال - al-Anfāl [SPOILS OF WAR]",
            "التوبة - at-Tawbah [REPENTANCE]",
            "يونس - Yūnus [JONAH]",
            "هود - Hūd [HUD]",
            "يوسف - Yūsuf [JOSEPH]",
            "الرعد - ar-Ra‘d [THUNDER]",
            "إبراهيم - Ibrāhīm [ABRAHAM]",
            "الحجر - al-Hijr [THE ROCKY TRACT]",
            "النحل - an-Nahl [THE BEE]",
            "الإسراء - al-Isrā’ [THE NIGHT JOURNEY]",
            "الكهف - al-Kahf [THE CAVE]",
            "مريم - Maryam [MARY]",
            "طه - Tāhā [TA-HA]",
            "الأنبياء - al-Ambiyā’ [THE PROPHETS]",
            "الحج - al-Hajj [THE PILGRIMAGE]",
            "المؤمنون - al-Mu’minūn [THE BELIEVERS]",
            "النور - an-Nūr [THE LIGHT]",
            "الفرقان - al-Furqān [THE CRITERION]",
            "الشعراء - ash-Shu‘arā’ [THE POETS]",
            "النمل - an-Naml [THE ANTS]",
            "القصص - al-Qasas [THE NARRATIVES]",
            "العنكبوت - al-‘Ankabūt [THE SPIDER]",
            "الروم - ar-Rūm [THE ROMAN EMPIRE]",
            "لقمان - Luqmān [LUQMAN]",
            "السجدة - as-Sajdah [PROSTRATION]",
            "الأحزاب - al-Ahzāb [THE CONFEDERATES]",
            "سبأ - Saba’ [SHEBA]",
            "فاطر - Fātir [THE ORIGINATOR]",
            "يس - Yāsīn [YA-SIN]",
            "الصافات - as-Sāffāt [THOSE RANGED IN RANKS]",
            "ص - Sād [SAD]",
            "الزمر - az-Zumar [THE CROWDS]",
            "غافر - Ghāfir [THE FORGIVING]",
            "فصلت - Fussilat [CLEARLY SPELLED OUT]",
            "الشورى - ash-Shūrā [THE CONSULTATION]",
            "الزخرف - az-Zukhruf [ORNAMENTS OF GOLD]",
            "الدخان - ad-Dukhān [SMOKE]",
            "الجاثية - al-Jāthiyah [THE KNEELING]",
            "الأحقاف - al-Ahqāf [THE SAND-DUNES]",
            "محمد - Muhammad [MUHAMMAD]",
            "الفتح - al-Fath [VICTORY]",
            "الحجرات - al-Hujurāt [THE APARTMENTS]",
            "ق - Qāf [QAF]",
            "الذاريات - adh-Dhāriyāt [THE SCATTERING WINDS]",
            "الطور - at-Tūr [MOUNT SINAI]",
            "النجم - an-Najm [THE STAR]",
            "القمر - al-Qamar [THE MOON]",
            "الرحمن - ar-Rahmān [THE MOST MERCIFUL]",
            "الواقعة - al-Wāqi‘ah [THE INEVITABLE EVENT]",
            "الحديد - al-Hadīd [IRON]",
            "المجادلة - al-Mujādalah [THE QUARREL]",
            "الحشر - al-Hashr [THE GATHERING]",
            "الممتحنة - al-Mumtahinah [THE WOMAN TO BE EXAMINED]",
            "الصف - as-Saff [BATTLE ARRAY]",
            "الجمعة - al-Jumu‘ah [FRIDAY, THE CONGREGATION]",
            "المنافقون - al-Munāfiqūn [THE HYPOCRITES]",
            "التغابن - at-Taghābun [MUTUAL LOSS AND GAIN]",
            "الطلاق - at-Talāq [DIVORCE]",
            "التحريم - at-Tahrīm [PROHIBITION]",
            "الملك - al-Mulk [DOMINION]",
            "القلم - al-Qalam [THE PEN]",
            "الحاقة - al-Hāqqah [THE CONCRETE REALITY]",
            "المعارج - al-Ma‘ārij [THE WAYS OF ASCENT]",
            "نوح - Nūh [NOAH]",
            "الجن - al-Jinn [THE JINN]",
            "المزمل - al-Muzzammil [FOLDED IN GARMENTS]",
            "المدثر - al-Muddaththir [THE ENWRAPPED]",
            "القيامة - al-Qiyāmah [THE RESURRECTION]",
            "الإنسان - al-Insān [MAN]",
            "المرسلات - al-Mursalāt [THE EMISSARIES]",
            "النبأ - an-Naba’ [THE NEWS]",
            "النازعات - an-Nāzi‘āt [THOSE WHO PULL]",
            "عبس - ‘Abasa [HE FROWNED]",
            "التكوير - at-Takwīr [SHROUDING IN DARKNESS]",
            "الإنفطار - al-Infitār [THE CLEAVING ASUNDER]",
            "المطففين - al-Mutaffifīn [DEALING IN FRAUD]",
            "الإنشقاق - al-Inshiqāq [THE SPLITTING ASUNDER]",
            "البروج - al-Burūj [THE ZODIAC]",
            "الطارق - at-Tāriq [THAT WHICH SEEMS AT NIGHT]",
            "الأعلى - al-A‘lā [THE MOST HIGH]",
            "الغاشية - al-Ghāshiyah [THE OVERWHELMING EVENT]",
            "الفجر - al-Fajr [THE DAWN]",
            "البلد - al-Balad [THE CITY]",
            "الشمس - ash-Shams [THE SUN]",
            "الليل - al-Layl [THE NIGHT]",
            "الضحى - ad-Duhā [THE FORENOON]",
            "الشرح - ash-Sharh [THE OPENING UP]",
            "التين - at-Tīn [THE FIG]",
            "العلق - al-‘Alaq [THE HANGING MASS]",
            "القدر - al-Qadr [DETERMINATION]",
            "البينة - al-Bayyinah [THE CLEAR EVIDENCE]",
            "الزلزلة - az-Zalzalah [THE EARTHQUAKE]",
            "العاديات - al-‘Ādiyāt [THOSE THAT RUN]",
            "القارعة - al-Qāri‘ah [RATTLING VIOLENT JERK AND THUNDER]",
            "التكاثر - at-Takāthur [PILING UP]",
            "العصر - al-‘Asr [THE TIME]",
            "الهمزة - al-Humazah [THE SLANDERER]",
            "الفيل - al-Fīl [THE ELEPHANT]",
            "قريش - al-Quraysh [QURAISH]",
            "الماعون - al-Mā‘ūn [THINGS OF COMMON USE]",
            "الكوثر - al-Kawthar [THE ABUNDANCE]",
            "الكافرون - al-Kāfirūn [THE UNBELIEVERS]",
            "النصر - an-Nasr [HELP]",
            "المسد - al-masad [THE TWISTED STRANDS]",
            "الإخلاص - al-Ikhlās [PURITY]",
            "الفلق - al-Falaq [THE FISSION]",
            "الناس - al-Nās [MEN]"
    },
            data2 = {"Ayah", "Ruku"};

    public OpenSpecificAyahRuku(Connection conn) {
        connection = conn;

        String[] data3 = new String[286];
        for (int i = 0; i < 286; i++) {
            data3[i] = String.valueOf(i + 1);
        }

        //Body
        surahName = new JLabel("Select Surah: ");
        selection = new JLabel("Select Ayah/Surah: ");
        number = new JLabel("Ayah/Ruku Number: ");

        surah = new JComboBox(data1);
        select = new JComboBox(data2);
        num = new JComboBox(data3);

        open = new JButton("Open");
        open.addActionListener(this);
        Font buttonFont = open.getFont();
        Font newMenuFont = buttonFont.deriveFont(16.0f);
        open.setFont(newMenuFont);

        p1 = new JPanel(new FlowLayout());
        p2 = new JPanel(new FlowLayout());
        p3 = new JPanel(new FlowLayout());
        body = new JPanel(new GridLayout(4, 1));
        footer = new JPanel(new FlowLayout());

        p1.add(surahName);
        p1.add(surah);
        p2.add(selection);
        p2.add(select);
        p3.add(number);
        p3.add(num);
        body.add(new JPanel());
        body.add(p1);
        body.add(p2);
        body.add(p3);
        footer.add(open);

        //Frame
        setLayout(new BorderLayout());
        add(body, BorderLayout.CENTER);
        add(footer, BorderLayout.SOUTH);
        setTitle("Select Your Choice");
        setBounds(400, 200, 600, 300);
        addWindowListener(new MyWindowListener());
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setVisible(true);
    }

    //---------------------------------------------------------------------------------------------------------
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "Open") {
            if (select.getSelectedItem() == "Ruku") {
                try {
                    getRukuData();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            } else if (select.getSelectedItem() == "Ayah") {
                try {
                    getAyahData();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    private void getRukuData() throws SQLException {
        String query = "select * from rukuData;";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        int s = 0, r = 0;
        for (int i = 0; i < 114; i++) {
            if (data1[i].equals(surah.getSelectedItem())) {
                s = i;
            }
        }
        r = Integer.parseInt((String) num.getSelectedItem());
        s++;
        boolean found = false;
        while (resultSet.next()) {
            if (s == Integer.parseInt(resultSet.getString(1))
                    && r == Integer.parseInt(resultSet.getString(2))) {
                imageViewer = new ImageViewer(connection);
                imageViewer.currentIndex = (Integer.parseInt(resultSet.getString(3))) - 2;
                imageViewer.imageViewer();
                found = true;
                break;
            }
        }
        if (!found) {
            JOptionPane.showMessageDialog(this, "Ruku Not Found!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void getAyahData() throws SQLException {
        String query = "select * from ayahData;";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        int s = 0, a = 0;
        for (int i = 0; i < 114; i++) {
            if (data1[i].equals(surah.getSelectedItem())) {
                s = i;
            }
        }
        a = Integer.parseInt((String) num.getSelectedItem());
        s++;
        boolean found = false;
        while (resultSet.next()) {
            if (s == Integer.parseInt(resultSet.getString(1))
                    && a == Integer.parseInt(resultSet.getString(2))) {
                imageViewer = new ImageViewer(connection);
                imageViewer.currentIndex = (Integer.parseInt(resultSet.getString(3))) - 2;
                imageViewer.imageViewer();
                found = true;
                break;
            }
        }
        if (!found) {
            JOptionPane.showMessageDialog(this, "Ayah Not Found!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //---------------------------------------------------------------------------------------------------------
    class MyWindowListener implements WindowListener {
        @Override
        public void windowOpened(WindowEvent e) {
        }

        @Override
        public void windowClosing(WindowEvent e) {
            dispose();
        }

        @Override
        public void windowClosed(WindowEvent e) {
        }

        @Override
        public void windowIconified(WindowEvent e) {
        }

        @Override
        public void windowDeiconified(WindowEvent e) {
        }

        @Override
        public void windowActivated(WindowEvent e) {
        }

        @Override
        public void windowDeactivated(WindowEvent e) {
        }
    }
    //---------------------------------------------------------------------------------------------------------
}
