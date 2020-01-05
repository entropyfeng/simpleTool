using System;
using System.Drawing;
using System.Windows.Forms;

/**
 *the coordinate of my game as follow:
 * o--1--2--3--4--5--6--7--8--9----------------------------->X
 * |
 * 1
 * |
 * 2
 * |
 * 3
 * |
 * 4
 * |
 * 5
 * |
 * 6
 * |
 * 7
 * |
 * Y
 */
namespace GluttonousSnake
{
    public partial class FormMain : Form
    {
        private Palette palette;

        public FormMain()
        {
            InitializeComponent();
            CheckForIllegalCrossThreadCalls = false;
        }
        private void pictureBox1_Paint(object sender, PaintEventArgs e) {
          
        }

        private void FormMain_KeyDown(object sender, KeyEventArgs e) {
            if ((e.KeyCode == Keys.Up || e.KeyCode == Keys.W)&&palette.theDirection!=Palette.Direction.DOWN) {
                palette.theDirection = Palette.Direction.UP;
                return;
            }
            if ((e.KeyCode == Keys.Down || e.KeyCode == Keys.S) && palette.theDirection != Palette.Direction.UP)
            {
                palette.theDirection = Palette.Direction.DOWN;
                return;
            }
            if ((e.KeyCode == Keys.Left || e.KeyCode == Keys.A )&& palette.theDirection != Palette.Direction.RIGHT)
            {
                palette.theDirection = Palette.Direction.LEFT;
                return;
            }
            if ((e.KeyCode == Keys.Right || e.KeyCode == Keys.D) && palette.theDirection != Palette.Direction.LEFT)
            {
                palette.theDirection = Palette.Direction.RIGHT;
                return;
            }
        }


    
        private void beginGameToolStripMenuItem_Click(object sender, EventArgs e)
        {
            pictureBox1.BorderStyle = BorderStyle.FixedSingle;
            int width = 40;
            int height = 40;
            int size = 15;

            pictureBox1.Width = width * size;
            pictureBox1.Height = height * size;
            this.Width = pictureBox1.Width + 30;
            this.Height = pictureBox1.Height + 60;
            palette = new Palette(width, height, size, pictureBox1.BackColor, Graphics.FromHwnd(pictureBox1.Handle),this);
            palette.start();
        }
    }
}
