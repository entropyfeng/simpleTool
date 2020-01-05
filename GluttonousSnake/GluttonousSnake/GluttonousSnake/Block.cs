using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Drawing;

namespace GluttonousSnake
{
    class Block
    {

        private Color color;

        private int size;

        private Point point;

        public Block(Color _color, int _size, Point _point) {
            this.point = _point;
            this.size = _size;
            this.color = _color;
        }

        public Point Point{
            get{ return this.point; }
        }
        public virtual void Paint(Graphics graphics) {

            SolidBrush brush = new SolidBrush(color);
            lock (graphics) {
                try
                {
                    graphics.FillRectangle(brush,this.point.X*this.size,this.point.Y*this.size,this.size-1,this.size-1);
              
                }
                catch {
                    Console.WriteLine("find exception in paint");
                }
                
            }
        }
    }
}
