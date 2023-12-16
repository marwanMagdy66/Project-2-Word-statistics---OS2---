import java.awt.event.*;
import java.io.*;
import java.util.List;
import java.util.concurrent.Semaphore;
import javax.swing.*;

public class Project_OS2 extends JFrame implements ActionListener {
    JButton Browse, Start;
    JLabel Dir, files, words, is, are, you, LongestWordFile, ShortestWordFile, Long, Short, subDIR;
    JTextField DirTxt, file1, file2, file3, word1, is1, are1, you1, LongestWordFile1, ShortestWordFile1, Longest, Shortest,
            word2, is2, are2, you2, LongestWordFile2, ShortestWordFile2, word3, is3, are3, you3, LongestWordFile3,
            ShortestWordFile3;
    JCheckBox sub;
    JFrame frame;

    private String overallLongest = "";
    private String overallShortest = "";

    private final Semaphore countersSemaphore = new Semaphore(1);
    private final Semaphore longestShortestSemaphore = new Semaphore(1);

    private SwingWorker<Void, String> worker1;
    private SwingWorker<Void, String> worker2;
    private SwingWorker<Void, String> worker3;

    public Project_OS2() {
        this.setTitle("THREADS");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.setLayout(null);

        Browse = new JButton("Browse");
        Start = new JButton("Start Processing");
        Dir = new JLabel("Directory : ");
        files = new JLabel("#Files");
        words = new JLabel("#Words");
        is = new JLabel("#is");
        are = new JLabel("#are");
        you = new JLabel("#you");
        LongestWordFile = new JLabel("#Longest");
        ShortestWordFile = new JLabel("#Shortest");
        Long = new JLabel("Longest");
        Short = new JLabel("Shortest");
        DirTxt = new JTextField("");
        file1 = new JTextField("");
        file2 = new JTextField("");
        file3 = new JTextField("");
        word1 = new JTextField("");
        is1 = new JTextField("");
        are1 = new JTextField("");
        you1 = new JTextField("");
        LongestWordFile1 = new JTextField("");
        ShortestWordFile1 = new JTextField("");
        Longest = new JTextField("");
        Shortest = new JTextField("");
        word2 = new JTextField("");
        is2 = new JTextField("");
        are2 = new JTextField("");
        you2 = new JTextField("");
        LongestWordFile2 = new JTextField("");
        ShortestWordFile2 = new JTextField("");
        word3 = new JTextField("");
        is3 = new JTextField("");
        are3 = new JTextField("");
        you3 = new JTextField("");
        LongestWordFile3 = new JTextField("");
        ShortestWordFile3 = new JTextField("");
        sub = new JCheckBox();
        subDIR = new JLabel("include subdirectories");

        this.add(Browse);
        Browse.setBounds(630, 30, 130, 30);
        this.add(Dir);
        Dir.setBounds(10, 20, 200, 50);

        this.add(DirTxt);
        DirTxt.setBounds(90, 40, 300, 20);
        this.add(files);
        files.setBounds(80, 100, 200, 50);
        this.add(words);
        words.setBounds(170, 100, 200, 50);
        this.add(is);
        is.setBounds(260, 100, 200, 50);
        this.add(are);
        are.setBounds(350, 100, 200, 50);
        this.add(you);
        you.setBounds(440, 100, 200, 50);
        this.add(LongestWordFile);
        LongestWordFile.setBounds(530, 100, 200, 50);
        this.add(ShortestWordFile);
        ShortestWordFile.setBounds(620, 100, 200, 50);
        this.add(file1);
        file1.setBounds(70, 150, 60, 20);
        this.add(file2);
        file2.setBounds(70, 200, 60, 20);
        this.add(file3);
        file3.setBounds(70, 250, 60, 20);
        this.add(word1);
        word1.setBounds(160, 150, 60, 20);
        this.add(word2);
        word2.setBounds(160, 200, 60, 20);
        this.add(word3);
        word3.setBounds(160, 250, 60, 20);
        this.add(is1);
        is1.setBounds(250, 150, 60, 20);
        this.add(is2);
        is2.setBounds(250, 200, 60, 20);
        this.add(is3);
        is3.setBounds(250, 250, 60, 20);
        this.add(are1);
        are1.setBounds(340, 150, 60, 20);
        this.add(are2);
        are2.setBounds(340, 200, 60, 20);
        this.add(are3);
        are3.setBounds(340, 250, 60, 20);
        this.add(you1);
        you1.setBounds(430, 150, 60, 20);
        this.add(you2);
        you2.setBounds(430, 200, 60, 20);
        this.add(you3);
        you3.setBounds(430, 250, 60, 20);
        this.add(LongestWordFile1);
        LongestWordFile1.setBounds(520, 150, 60, 20);
        this.add(LongestWordFile2);
        LongestWordFile2.setBounds(520, 200, 60, 20);
        this.add(LongestWordFile3);
        LongestWordFile3.setBounds(520, 250, 60, 20);
        this.add(ShortestWordFile1);
        ShortestWordFile1.setBounds(610, 150, 60, 20);
        this.add(ShortestWordFile2);
        ShortestWordFile2.setBounds(610, 200, 60, 20);
        this.add(ShortestWordFile3);
        ShortestWordFile3.setBounds(610, 250, 60, 20);
        this.add(Long);
        Long.setBounds(70, 350, 60, 20);
        this.add(Short);
        Short.setBounds(70, 400, 60, 20);
        this.add(Longest);
        Longest.setBounds(160, 350, 100, 20);
        this.add(Shortest);
        Shortest.setBounds(160, 400, 100, 20);
        this.add(Start);
        Start.setBounds(50, 500, 700, 30);
        this.add(subDIR);
        subDIR.setBounds(110, 70, 150, 20);
        this.add(sub);
        sub.setBounds(50, 70, 30, 20);
        Browse.addActionListener(this);
        Start.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Browse) {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int returnVal = chooser.showOpenDialog(frame);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                DirTxt.setText(chooser.getSelectedFile().getAbsolutePath());

                populateFileTextFields(chooser.getSelectedFile());
            }
        } else if (e.getSource() == Start) {
            startProcessing();
        }
    }

    private void populateFileTextFields(File directory) {
        File[] files = directory.listFiles();

        if (files != null && files.length >= 1) {
            file1.setText(files[0].getName());
        }

        if (files != null && files.length >= 2) {
            file2.setText(files[1].getName());
        }

        if (files != null && files.length >= 3) {
            file3.setText(files[2].getName());
        }
    }

    private void startProcessing() {
        worker1 = createProcessingWorker(new File(DirTxt.getText(), file1.getText()), word1, is1, are1, you1,
                LongestWordFile1, ShortestWordFile1);
        worker2 = createProcessingWorker(new File(DirTxt.getText(), file2.getText()), word2, is2, are2, you2,
                LongestWordFile2, ShortestWordFile2);
        worker3 = createProcessingWorker(new File(DirTxt.getText(), file3.getText()), word3, is3, are3, you3,
                LongestWordFile3, ShortestWordFile3);

        worker1.execute();
        worker2.execute();
        worker3.execute();
    }

    private SwingWorker<Void, String> createProcessingWorker(File file, JTextField wordField, JTextField isField,
            JTextField areField, JTextField youField, JTextField llField, JTextField ssField) {
        return new SwingWorker<Void, String>() {
            @Override
            protected Void doInBackground() throws Exception {
                int wordCount = 0;
                int isCount = 0;
                int areCount = 0;
                int youCount = 0;
                String llCount = "";
                String ssCount = "";
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] words = line.split("\\s+");
                        wordCount += words.length;

                        for (String word : words) {
                            switch (word) {
                                case "is" -> isCount++;
                                case "are" -> areCount++;
                                case "you" -> youCount++;
                                default -> {
                                }
                            }

                            if (word.length() > llCount.length()) {
                                llCount = word;
                            }

                            if (ssCount.isEmpty() || word.length() < ssCount.length()) {
                                ssCount = word;
                            }
                        }

                        publish(String.valueOf(wordCount), String.valueOf(isCount),
                                String.valueOf(areCount), String.valueOf(youCount), llCount, ssCount);
                    }
                }

                return null;
            }

            @Override
            protected void process(List<String> chunks) {
                // This method is called when the publish method is invoked
                String lastWordCount = chunks.get(chunks.size() - 6);
                String lastIsCount = chunks.get(chunks.size() - 5);
                String lastAreCount = chunks.get(chunks.size() - 4);
                String lastYouCount = chunks.get(chunks.size() - 3);
                String lastLlCount = chunks.get(chunks.size() - 2);
                String lastSsCount = chunks.get(chunks.size() - 1);
            
                // Acquire semaphore for thread-safe access to counters
                try {
                    countersSemaphore.acquire();
            
                    SwingUtilities.invokeLater(() -> {
                        wordField.setText(lastWordCount);
                        isField.setText(lastIsCount);
                        areField.setText(lastAreCount);
                        youField.setText(lastYouCount);
                        llField.setText(lastLlCount);
            
                        ssField.setText(lastSsCount);
            
                     
                        updateOverallLongestAndShortest(lastLlCount, lastSsCount);
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countersSemaphore.release();
                }
            }
            
        };
    }

    private void updateOverallLongestAndShortest(String currentLongest, String currentShortest) {
        try {
            longestShortestSemaphore.acquire();

            if (currentLongest.length() > overallLongest.length()) {
                overallLongest = currentLongest;
            }

            if (currentShortest.length() < overallShortest.length() || overallShortest.isEmpty()) {
                overallShortest = currentShortest;
            }

            SwingUtilities.invokeLater(() -> {
                Longest.setText(overallLongest);
                Shortest.setText(overallShortest);
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            longestShortestSemaphore.release();
        }
    }

    public static void main(String[] args) {
        new Project_OS2();
    }
}
