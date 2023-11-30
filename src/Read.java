import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Read extends JFrame implements ActionListener {
    private Connection connection;
    private ImageViewer imageViewer;
    private int status;
    private JButton b1, b2, b3;
    private JScrollPane scrollPane;
    private JPanel body;
    private List<JButton> paras, surahs, manazil;
    private HashMap<String, Integer> para = new HashMap<>(), surah = new HashMap<>(), manzil = new HashMap<>();
    private int[] id1 = {0, 19, 37, 55, 73, 91, 109, 127, 145, 163, 181, 199, 217, 235, 253, 271, 289, 307,
            325, 343, 361, 379, 397, 415, 433, 451, 469, 487, 507, 527},
            id2 = {0, 1, 44, 68, 95, 114, 135, 158, 167, 186, 198, 211, 223, 229, 234, 239, 253, 263,
                    274, 280, 289, 298, 307, 314, 323, 329, 338, 346, 356, 363, 369, 372, 375, 384, 390, 395, 400,
                    407, 411, 419, 428, 433, 439, 445, 447, 451, 455, 459, 462, 465, 467, 470, 472, 474, 477, 480,
                    483, 487, 490, 494, 496, 498, 499, 501, 503, 505, 507, 509, 511, 513, 515, 517, 519, 520, 522,
                    523, 525, 527, 528, 529, 531, 531, 532, 533, 534, 535, 536, 536, 537, 538, 539, 539, 540, 540,
                    541, 541, 542, 542, 543, 543, 543, 544, 544, 544, 545, 545, 545, 545, 546, 546, 546, 546, 547, 547},
            id3 = {0, 95, 186, 253, 329, 400, 465};
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
            data2 = {
                    "آلم (Alīf-Lām-Mīm)",
                    "سَيَقُولُ (Sayaqūlu)",
                    "تِلْكَ ٱلْرُّسُلُ (Tilka ’r-Rusulu)",
                    "لن تنالوا (Lan Tana Lu)",
                    "وَٱلْمُحْصَنَاتُ (Wa’l-muḥṣanātu)",
                    "لَا يُحِبُّ ٱللهُ (Lā yuḥibbu-’llāhu)",
                    "وَإِذَا سَمِعُوا (Wa ’Idha Samiʿū)",
                    "وَلَوْ أَنَّنَا (Wa-law annanā)",
                    "قَالَ ٱلْمَلَأُ (Qāla ’l-mala’u)",
                    "وَٱعْلَمُواْ (Wa-’aʿlamū)",
                    "يَعْتَذِرُونَ (Yaʿtazerūn)",
                    "وَمَا مِنْ دَآبَّةٍ (Wa mā min dābbatin)",
                    "وَمَا أُبَرِّئُ (Wa mā ubarri’u)",
                    "رُبَمَا (Rubamā)",
                    "سُبْحَانَ ٱلَّذِى (Subḥāna ’lladhī)",
                    "قَالَ أَلَمْ (Qāla ’alam)",
                    "ٱقْتَرَبَ لِلْنَّاسِ (Iqtaraba li’n-nāsi)",
                    "قَدْ أَفْلَحَ (Qad ’aflaḥa)",
                    "وَقَالَ ٱلَّذِينَ (Wa-qāla ’lladhīna)",
                    "أَمَّنْ خَلَقَ (’A’man Khalaqa)",
                    "أُتْلُ مَاأُوْحِیَ (Otlu ma oohiya)",
                    "وَمَنْ يَّقْنُتْ (Wa-man yaqnut)",
                    "وَمَآ لي (Wa-Mali)",
                    "فَمَنْ أَظْلَمُ (Fa-man ’aẓlamu)",
                    "إِلَيْهِ يُرَدُّ (Ilayhi yuraddu)",
                    "حم (Ḥā’ Mīm)",
                    "قَالَ فَمَا خَطْبُكُم (Qāla fa-mā khaṭbukum)",
                    "قَدْ سَمِعَ ٱللهُ (Qad samiʿa ’llāhu)",
                    "تَبَارَكَ ٱلَّذِى (Tabāraka ’lladhī)",
                    "عَمَّ (‘Amma)"
            },
            data3 = {
                    "1.الفاتحة - al-Fātihah [THE OPENING]",
                    "2.المائدة - al-Mā’idah [THE TABLE SPREAD]",
                    "3.يونس - Yūnus [JONAH]",
                    "4.الإسراء - al-Isrā’ [THE NIGHT JOURNEY]",
                    "5.الشعراء - ash-Shu‘arā’ [THE POETS]",
                    "6.الصافات - as-Sāffāt [THOSE RANGED IN RANKS]",
                    "7.ق - Qāf [QAF]"
            };

    //---------------------------------------------------------------------------------------------------------
    public Read(Connection conn) {
        connection = conn;
    }

    public void startReading() {
        //Frame
        setLayout(new BorderLayout());
        setTitle("E-Qura'an Application");
        setBounds(400, 10, 600, 700);
        addWindowListener(new MyWindowListener());
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setVisible(true);

        //Header
        b1 = new JButton("Para No.");
        b1.addActionListener(this);
        b2 = new JButton("Surah No.");
        b2.addActionListener(this);
        b3 = new JButton("Manzil");
        b3.addActionListener(this);

        JPanel header = new JPanel(new GridLayout(1, 3));
        header.add(b1);
        header.add(b2);
        header.add(b3);

        //Body
        paras = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            String st = (i + 1) + ".  " + data2[i];
            JButton temp = new JButton(st);
            temp.addActionListener(this);
            paras.add(temp);
            para.put(st, id1[i]);
        }
        surahs = new ArrayList<>();
        for (int i = 0; i < 114; i++) {
            String st = (i + 1) + ".  " + data1[i];
            JButton temp = new JButton(st);
            temp.addActionListener(this);
            surahs.add(temp);
            surah.put(st, id2[i]);
        }
        manazil = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            String st = data3[i];
            JButton temp = new JButton(st);
            temp.addActionListener(this);
            manazil.add(temp);
            manzil.put(st, id3[i]);
        }

        sortByPara();

        //Frame
        add(header, BorderLayout.NORTH);
    }

    public void continueReading() throws SQLException {
        imageViewer = new ImageViewer(connection);
        String query = "select * from savedData;";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            int x = Integer.parseInt(resultSet.getString(1));
            if (x == -1) {
                JOptionPane.showMessageDialog(this, "No Data Found!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            imageViewer.currentIndex = x;
        }
        imageViewer.imageViewer();
    }

    //---------------------------------------------------------------------------------------------------------
    private void sortByPara() {
        status = 1;
        b1.setBackground(Color.GREEN);
        b2.setBackground(Color.ORANGE);
        b3.setBackground(Color.ORANGE);
        if (scrollPane != null) {
            remove(scrollPane);
        }
        body = new JPanel(new GridLayout(30, 1));
        for (int i = 0; i < 30; i++) {
            body.add(paras.get(i));
        }
        scrollPane = new JScrollPane(body);
        add(scrollPane, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void sortBySurah() {
        status = 2;
        b2.setBackground(Color.GREEN);
        b1.setBackground(Color.ORANGE);
        b3.setBackground(Color.ORANGE);
        remove(scrollPane);
        body = new JPanel(new GridLayout(114, 1));
        for (int i = 0; i < 114; i++) {
            body.add(surahs.get(i));
        }
        scrollPane = new JScrollPane(body);
        add(scrollPane, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void sortByManzil() {
        status = 3;
        b3.setBackground(Color.GREEN);
        b1.setBackground(Color.ORANGE);
        b2.setBackground(Color.ORANGE);
        remove(scrollPane);
        body = new JPanel(new GridLayout(7, 1));
        for (int i = 0; i < 7; i++) {
            body.add(manazil.get(i));
        }
        scrollPane = new JScrollPane(body);
        add(scrollPane, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    //---------------------------------------------------------------------------------------------------------
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Para No." -> sortByPara();
            case "Surah No." -> sortBySurah();
            case "Manzil" -> sortByManzil();
            default -> {
                imageViewer = new ImageViewer(connection);
                if (status == 1) {
                    this.imageViewer.currentIndex = this.para.get(e.getActionCommand());
                } else if (status == 2) {
                    this.imageViewer.currentIndex = this.surah.get(e.getActionCommand());
                } else if (status == 3) {
                    this.imageViewer.currentIndex = this.manzil.get(e.getActionCommand());
                }
                this.imageViewer.imageViewer();
            }
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
