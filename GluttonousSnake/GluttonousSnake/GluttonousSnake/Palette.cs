using System;
using System.Drawing;
using System.Collections;
using System.Timers;
using System.Windows.Forms;
namespace GluttonousSnake
{
    class Palette
    {

        //宽度
        private int width=20;
        //高度
        private int height=20;
        //背景颜色
        private Color backgroundColor;
        //画布
        private Graphics gpPalette;
        //蛇块列表
        private ArrayList blocks;

        //定时器
        private System.Timers.Timer timer;
        //当前食物
        private Block food;
        //单位大小
        private int size = 20;

        private int[] speed = new int[] { 500, 400, 300, 200, 100 };
        //游戏等级
        private int level = 0;

        private bool isGameover = false;

        private Form form;
        public Palette(int width, int height, int size, Color backgroundColor, Graphics graphics,Form form) {
            this.width = width;
            this.height = height;
            this.size = size;
            this.backgroundColor = backgroundColor;
            this.gpPalette = graphics;
            this.blocks = new ArrayList();
            this.blocks.Insert(0, new Block(Color.Red, size, new Point(width / 2, height / 2)));
            this.theDirection = Direction.LEFT;
            this.form = form;
        }

 
        private Block getFood() {
            Block tempFood = null;
            Random random = new Random();
            bool redo = false;
            while (!redo) {
          
                Block tempBlock=null;
                int x = random.Next(this.width);
        
                int y = random.Next(this.height);
               
                for (int i = 0; i < blocks.Count; i++) {
                    tempBlock = (Block)this.blocks[i];
                    if (tempBlock.Point.X == x && tempBlock.Point.Y == y) {
                        redo = true;
                    }
                }
                if (redo == false)
                {
                    tempFood = new Block(Color.Black,this.size,new Point(x,y));
                }
            }
            return tempFood;
        }
        //游戏开始
        public void start() {
            this.food = getFood();
            timer = new System.Timers.Timer(speed[level]);
            timer.Elapsed += new ElapsedEventHandler(onBlockEvent);
            timer.AutoReset = true;
            timer.Start();


        }
        public Direction theDirection { get; set; }

        private void move() {

            Point nextPoint;
            Block head = (Block)blocks[0];
            if (this.theDirection == Direction.UP)
            {

                nextPoint = new Point(head.Point.X, head.Point.Y - 1);
            }
            else if (this.theDirection == Direction.DOWN)
            {
                nextPoint = new Point(head.Point.X, head.Point.Y + 1);
            }
            else if (this.theDirection == Direction.LEFT)
            {
                nextPoint = new Point(head.Point.X - 1, head.Point.Y);
            }
            else {
                nextPoint = new Point(head.Point.X+1, head.Point.Y);
            }

            Block nextBlock = new Block(Color.Red, this.size, nextPoint);

            //如果食物坐标与下个坐标相等，则生成一个新食物
            if (this.food.Point == nextPoint)
            {

               food=getFood();
            }
            else {
                this.blocks.RemoveAt(this.blocks.Count - 1);
            }
            this.blocks.Insert(0, nextBlock);
            form.Text = "贪吃蛇（当前得分" + blocks.Count + " 分）";
            paintPalette(this.gpPalette);
        }

        public void paintPalette(Graphics graphics) {
            graphics.Clear(this.backgroundColor);
            this.food.Paint(graphics);
            foreach (Block b in blocks) {
                b.Paint(graphics);
            }

        }
        public bool checkDead() {
            Block head = (Block)blocks[0];

            if (head.Point.X <= 0 || head.Point.Y <= 0 || head.Point.X>=this.width||head.Point.Y>=this.height) {
                return true;
            }
            Block tempBlock;
            for (int i = 1; i < blocks.Count; i++) {
                tempBlock = (Block)blocks[i];
                if (head.Point.X == tempBlock.Point.X && head.Point.Y == tempBlock.Point.Y) {
                    this.isGameover = true;
                    return true;
                }
            }

            return false;
        }

        private void onBlockEvent(object source,ElapsedEventArgs e) {
            this.move();

            if (checkDead()) {
                timer.Stop();
                timer.Dispose();
                System.Windows.Forms.MessageBox.Show("你的得分是" + this.blocks.Count+" 分。", "Game over！！");
            }
        }
        public enum Direction {

            LEFT,
            RIGHT,
            UP,
            DOWN
        }
    }
}
