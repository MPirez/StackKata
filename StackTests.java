import PracticeOne.BoundedStack;
import PracticeOne.Stack;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class StackTests {
    private Stack stack;

    @Before
    public void setUp() throws Exception {
        stack = BoundedStack.Make(2);
    }

    @Test
    public void newlyCreateStack_ShouldBeEmpty() throws Exception {
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.getSize());
    }

    @Test
    public void AfterOnePush_StackSizeShouldBeOne() throws Exception {
        stack.push(1);
        assertEquals(1, stack.getSize());
        assertFalse(stack.isEmpty());
    }

    @Test
    public void afterOnePushAndOnePop_ShouldBeEmpty() throws Exception {
        stack.push(1);
        stack.pop();
        assertTrue(stack.isEmpty());
    }

    @Test(expected = Stack.Overflow.class)
    public void WhenPushedPastLimit_StackOverFlows() throws Exception {
        stack.push(1);
        stack.push(1);
        stack.push(1);
    }

    @Test(expected = Stack.Underflow.class)
    public void WhenEmptyStackIsPopped_ShouldThrowUnderflow() throws Exception {
        stack.pop();
    }

    @Test
    public void WhenOneIsPushed_OneIsPopped() throws Exception {
        stack.push(1);
        assertEquals(1, stack.pop());
    }

    @Test
    public void whenOneAndTwoArePushed_TwoAndOneArePopped() throws Exception {
        stack.push(1);
        stack.push(2);
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
    }

    @Test(expected = Stack.IllegalCapacity.class)
    public void whenCreatingStackWithNegativeSize_ShouldThrowIllegalCapacity() throws Exception {
        BoundedStack.Make(-1);
    }

    @Test(expected = Stack.Overflow.class)
    public void whenCreatingAStackWithZeroCapacity_AnyPushShouldOverflow() throws Exception {
        stack = BoundedStack.Make(0);
        stack.push(1);
    }

    @Test
    public void whenOneIsPushed_OneIsOnTop() throws Exception {
        stack.push(1);
        assertEquals(1, stack.top());
    }

    @Test(expected = Stack.Empty.class)
    public void WhenStackIsEmpty_TopThrowsEmpty() throws Exception {
        stack.top();
    }

    @Test(expected = Stack.Empty.class)
    public void WithZeroCapacityStack_topThrowsEmpty() throws Exception {
        stack = BoundedStack.Make(0);
        stack.top();
    }

    @Test
    public void GivenStackWithOneTwoPushed_FindOne() throws Exception {
        stack.push(1);
        stack.push(2);
        //searching by index
        int oneIndex=stack.find(1);
        int twoIndex=stack.find(2);
        assertEquals(1, oneIndex);
        assertEquals(0, twoIndex);
    }

    @Test
    public void GivenStackWithNo2_Find2ShouldReturnNull() throws Exception{
        assertNull(stack.find(2));

    }
}
