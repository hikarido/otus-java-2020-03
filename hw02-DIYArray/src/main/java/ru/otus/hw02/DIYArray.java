package ru.otus.hw02;

import java.lang.reflect.Array;
import java.util.*;

public class DIYArray<Value> implements List<Value>{
	/**
	 * Creates empty array
	 * Required by Collection java doc
	 */
	public DIYArray(){
		data = allocBySize(INIT_CAPACITY);
		this.capacity = INIT_CAPACITY;
		this.size = 0;
	}

	public DIYArray(int capacity){
		data = allocBySize(capacity);
		this.capacity = capacity;
		this.size = 0;
	}

	/**
	 * Makes copy of passed collection
	 * Required by javadoc
	 * @param collection
	 */
	public void DIYArray(Collection<? extends Value> collection){
		throw new UnsupportedOperationException();
	}

	@Override
	public int size(){
		return size;
	}

	public int getCapacity(){
		return capacity;
	}

	@Override
	public boolean isEmpty(){
		if(size == 0){
			return true;
		}

		return false;
	}

	@Override
	public boolean contains(Object o){
		for(int i = 0; i < size; i++){
			Object element = data[i];
			if(Objects.equals(element, o)){
				return true;
			}
		}

		return false;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Iterator<Value> iterator() {
		return new Iterator<Value>() {
			int position = 0;
			@Override
			public boolean hasNext() {
				return isIndexInBound(position);
			}

			@Override
			public Value next() {
				if(!isIndexInBound(position)){
					throw new NoSuchElementException("end of array was reached");
				}

				Value v = (Value) data[position];
				position += 1;
				return v;
			}
		};
	}

	@Override
	public Object[] toArray(){
		return Arrays.copyOf(data, size);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T[] toArray(T[] a){

		// мы не помещаемся в a
		if(a.length < size){
			return (T[]) Arrays.copyOf(data, size, a.getClass());
		}

		// мы помещаемся в a
		System.arraycopy(data, 0, a, 0, size);
		if(a.length > size){
			a[size] = null;
		}

		return a;
	}

	@Override
	public boolean add(Value e){
		if(size == capacity){
			data = grow();
		}
		data[size] = e;
		size += 1;
		return true;
	}

	@Override
	public boolean remove(Object o){
		for(int i = 0; i < size; i++){
			if(Objects.equals(data[i], o)){
				removeByIndexWithLeftShift(i);
				return true;
			}
		}

		return false;
	}

	@Override
	public ListIterator<Value> listIterator() {
		// TODO implemet it
		return null;
	}

	@Override
	public ListIterator<Value> listIterator(int index) {
		// TODO implemet it
		return null;
	}

	@Override
	public Value get(int index) {
		if(isIndexInBound(index)){
			return (Value) data[index];
		}

		throw new ArrayIndexOutOfBoundsException("Size: " + size + ". Index: " + index);
	}

	@Override
	public Value set(int index, Value element) {
		if(isIndexInBound(index)){
			Value old = (Value) data[index];
			data[index] = element;
			return old;

		}

		throw new ArrayIndexOutOfBoundsException("Size: " + size + ". Index: " + index);
	}

	@Override
	public String toString(){
		if(size == 0){
			return "[  ]";
		}

		StringBuilder bld = new StringBuilder(size);

		bld.append("[ ");
		bld.append(Objects.toString(data[0]));
		for(int i = 1; i < size; i++){
			bld.append(" ");
			bld.append(Objects.toString(data[i]));
		}
		bld.append(" ]");

		return bld.toString();
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(Collection<? extends Value> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(int index, Collection<? extends Value> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		throw new UnsupportedOperationException();
	}


	@Override
	public void add(int index, Value element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Value remove(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int indexOf(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int lastIndexOf(Object o) {
		throw new UnsupportedOperationException();
	}


	@Override
	public List<Value> subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException();
	}

	private boolean isIndexInBound(int index){
		if(index >= 0 && index < size){
			return true;
		}

		return false;
	}

	private void removeByIndexWithLeftShift(int index){
		if(size == capacity){
			size -= 1;
			return;
		}

		System.arraycopy(data, index + 1, data, index, size - index);
		size -= 1;
	}

	@SuppressWarnings("unchecked")
	private Value[] grow(){
		capacity *= 2;
		return  (Value[]) Arrays.copyOf(data, capacity);
	}

	private Object[] allocBySize(int size) {
		return new Object[size];
	}

	private Object[] data;
	private int size = 0;
	private int capacity = 0;
	private static int INIT_CAPACITY = 10;
}
